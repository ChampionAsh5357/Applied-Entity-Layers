package net.ashwork.mc.appliedentitylayers.client.model.manager;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import net.ashwork.mc.appliedentitylayers.api.client.model.armor.ArmorRenderer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

// TODO: Document, implement
public class ArmorModelManager {

    private final Map<EntityType<?>, ArmorRenderer> defaultArmorModels;
    private final Table<EntityType<?>, Item, ArmorRenderer> customArmorModels;
    private final Map<EntityType<?>, EntityType<?>> similarEntityTextures;
    private final Map<String, ResourceLocation> armorTextureCache;

    public ArmorModelManager() {
        this.defaultArmorModels = new IdentityHashMap<>();
        this.customArmorModels = HashBasedTable.create();
        this.similarEntityTextures = new IdentityHashMap<>();
        this.armorTextureCache = new HashMap<>();
    }

    void applyLayers(EntityModelSet modelSet) {
        this.defaultArmorModels.forEach((type, renderer) -> renderer.createModels(modelSet));
    }

    private class DefaultArmorRenderer<T extends Model> implements ArmorRenderer {

        protected final BiFunction<EntityModelSet, ModelLayerLocation, T> modelFactory;
        protected final TriConsumer<T, Model, ArmorItem.Type> setup;
        protected final ModelLayerLocation layer;
        private T model;

        public DefaultArmorRenderer(Function<ModelPart, T> modelFactory, TriConsumer<T, Model, ArmorItem.Type> setup, ModelLayerLocation layer) {
            this.modelFactory = (modelSet, defn) -> modelFactory.apply(modelSet.bakeLayer(defn));
            this.layer = layer;
            this.setup = setup;
        }

        protected T getModel(ArmorItem.Type type) {
            return this.model;
        }

        @Override
        public void createModels(EntityModelSet modelSet) {
            this.model = this.modelFactory.apply(modelSet, this.layer);
        }

        @Override
        public Model getAndSetup(LivingEntity entity, ItemStack stack, ArmorItem.Type type, Model parent) {
            var model = this.getModel(type);
            this.setup.accept(model, parent, type);
            return model;
        }

        @Override
        public RenderType getType(LivingEntity entity, ItemStack stack, ArmorItem.Type type, ResourceLocation texture) {
            var model = this.getModel(type);
            return model.renderType(texture);
        }

        @Override
        public ResourceLocation getTexture(LivingEntity entity, ItemStack stack, ArmorItem.Type type, @Nullable String suffix) {
            if (stack.getItem() instanceof ArmorItem armor) {
                // Check if entity type is registered to use the same textures as another entity
                @Nullable ResourceLocation entityName = ForgeRegistries.ENTITY_TYPES.getKey(ArmorModelManager.this.similarEntityTextures.getOrDefault(entity.getType(), entity.getType()));
                var materialName = armor.getMaterial().getName().split(":");
                // <armor_material_namespace>:textures/models/armor/<entity_type_namespace>/<entity_type_path>/<armor_material_path>_layer_<1/2>[_<suffix>].png
                var texture = String.format("%s:textures/models/armor/%s%s_layer_%d%s.png",
                        // If no ':', set to minecraft
                        materialName.length == 1 ? "minecraft" : materialName[0],
                        // If null, use default humanoid textures
                        entityName == null ? "" : entityName.getNamespace() + "/" + entityName.getPath() + "/",
                        // If no ':', get the first element
                        materialName.length == 1 ? materialName[0] : materialName[1],
                        // 2 if leggings, 1 otherwise
                        type == ArmorItem.Type.LEGGINGS ? 2 : 1,
                        // Apply suffix
                        suffix == null ? "" : "_" + suffix);
                return ArmorModelManager.this.armorTextureCache.computeIfAbsent(texture, ResourceLocation::new);
            }
            throw new IllegalArgumentException(stack + " must be an instance of ArmorItem to use the default implementation!");
        }
    }

    private class DoubleArmorRenderer<T extends Model> extends DefaultArmorRenderer<T> {

        private final ModelLayerLocation inner;
        private T innerModel;

        public DoubleArmorRenderer(Function<ModelPart, T> modelFactory, TriConsumer<T, Model, ArmorItem.Type> setup, ModelLayerLocation outer, ModelLayerLocation inner) {
            super(modelFactory, setup, outer);
            this.inner = inner;
        }

        @Override
        public void createModels(EntityModelSet modelSet) {
            super.createModels(modelSet);
            this.innerModel = this.modelFactory.apply(modelSet, this.inner);
        }

        @Override
        protected T getModel(ArmorItem.Type type) {
            return type == ArmorItem.Type.LEGGINGS ? this.innerModel : super.getModel(type);
        }
    }
}
