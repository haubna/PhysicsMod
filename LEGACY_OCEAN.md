#### Supporting versions before v188 (Ocean Physics 1.0)

Add the following files to the corresponding world folders inside your shaderpack:

```text
physics_ocean.vsh
physics_ocean.gsh
physics_ocean.fsh
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

The old v1 implementation of `oceans.glsl` is available here:

[View the legacy `oceans.glsl` implementation](https://github.com/haubna/PhysicsMod/blob/5202c88e0881969bc21b46c6a0559d0761afb175/oceans.glsl)
