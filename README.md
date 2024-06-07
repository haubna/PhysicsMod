# Physics Mod
Feel free to report any bugs here. Please only report bugs on the newest version of the mod. Legacy versions of Minecraft will not get updates.

# Shaders

## Ocean
From Pro v170 and onward you can now create and place custom ocean shader files in your shaderpacks (only supported with Iris). Simply place **physics_ocean.vsh**/**physics_ocean.gsh**/**physics_ocean.fsh** (.gsh file not mandatory) shader files in the corresponding world folders in your shaderpack (you can also create custom shadow shader files with **physics_ocean_shadow**). Here is a simple comparison from a traditional water shader changed to an ocean shader (thanks Emin for letting me share it!): https://www.diffchecker.com/24hNvzCu/ (Complementary Reimagined r5.1.1)

For more details about the code check out the oceans.glsl file on this page.

## Snow
Snow uses the entity ID (rendered with the entities shader) 829925 (uniform int entityId;).

# Ragdoll API (Physics Mod 2.2.3 and above)
You can use it to add ragdoll physics to your mod entities. To make the physics work with your mod __USE__ the __ModelPart__ class for your custom entities and not create your own one, since I hook into this class.
You will need to create a __RagdollHook__ and add it via __RagdollMapper.addHook(RagdollHook)__. A ragdoll hook example can be found in this repo. 

## RagdollHook
***filterCuboidsFromEntities*** is to filter excessive cubes from the model that can get created through some feature overlays in Minecraft and would cause multiple cube drops of the same model.

***map*** is needed to create the connections between the cubes. If you are not sure which cubes should get connected you can get some info via __RagdollMapper.printModelParts(model)__.

Thanks to fabmax for creating a PhysX JNI Wrapper: https://github.com/fabmax/physx-jni

Mod page: https://minecraftphysicsmod.com
