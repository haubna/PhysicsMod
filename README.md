# Physics Mod

Welcome to the official repository for **Physics Mod**.

Found a problem? Feel free to [report a bug](../../issues) or open an issue with feedback.

---

## Shaders

### Ocean Physics 2.0

Starting with **Physics Mod Pro v170**, shaderpack developers can create and include custom ocean shaders. The new v2 of Ocean Physics is only supported by **Physics Mod Pro v185** and upwards.

> [!NOTE]
> Custom ocean shaders are currently supported only when using **Iris**.

Add the following files to the corresponding world folders inside your shaderpack:

```text
physics_ocean_v2.vsh
physics_ocean_v2.gsh
physics_ocean_v2.fsh
```

The `.gsh` geometry shader is optional.

Custom shadow shaders are also supported using the following filename prefix:

```text
physics_ocean_shadow_v2
```

#### Example implementation

The comparison below demonstrates how a traditional water shader was converted into an ocean shader:

[View the shader comparison](https://www.diffchecker.com/24hNvzCu/)

Special thanks to **Emin** for allowing me to share this example, which is based on **Complementary Reimagined r5.1.1**.

> [!IMPORTANT]
> This comparison uses **Ocean Physics 1.0**. For the current **Ocean Physics 2.0** implementation, refer to the latest [`oceans.glsl`](https://github.com/haubna/PhysicsMod/blob/main/oceans.glsl) file.

#### Supporting versions before v185

To support Physics Mod versions earlier than **v185**, use the legacy filenames without the `v2` suffix:

```text
physics_ocean.vsh
physics_ocean.gsh
physics_ocean.fsh
```

The old v1 implementation of `oceans.glsl` is available here:

[View the legacy `oceans.glsl` implementation](https://github.com/haubna/PhysicsMod/blob/5202c88e0881969bc21b46c6a0559d0761afb175/oceans.glsl)

---

### Snow

Snow is rendered through the entity shader and uses the following entity ID:

```glsl
uniform int entityId;

// Snow entity ID
829925
```

Minecraft 26.2 and later only support it via the `mc_Entity` attribute. It also uses `829925` as ID.

---

## Ragdoll API

> Available in **Physics Mod 2.2.3 and newer**.

The Ragdoll API allows you to add ragdoll physics to entities from your own mod.

### Requirements

Your custom entities must use Minecraft's built-in `ModelPart` class.

> [!WARNING]
> Do not replace `ModelPart` with a custom implementation. Physics Mod hooks directly into this class to create the ragdoll physics.

Create a `RagdollHook` and register it using:

```java
RagdollMapper.addHook(ragdollHook);
```

A complete `RagdollHook` example is available in this repository.

### `RagdollHook` methods

#### `filterCuboidsFromEntities`

Use this method to remove excessive or duplicated cubes from an entity model.

Some Minecraft features and rendering overlays can generate multiple cubes for the same model part. Without filtering, these may result in duplicated physics objects or multiple cube drops.

#### `map`

Use this method to define the connections between the model's cubes.

When you are unsure which model parts should be connected, print the available model-part information with:

```java
RagdollMapper.printModelParts(model);
```

---

## Credits

Special thanks to **fabmax** for creating the PhysX JNI wrapper:

[github.com/fabmax/physx-jni](https://github.com/fabmax/physx-jni)

---

## Links

* [Official Physics Mod website](https://minecraftphysicsmod.com)
* [Report bugs and issues](../../issues)
