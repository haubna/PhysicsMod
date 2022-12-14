# Physics Mod
Feel free to report any bugs here. Please only report bugs on the newest version of the mod. Legacy versions of Minecraft will not get updates.

# Shaders
Snow uses the entity ID (rendered with the entities shader) 829925.

# Ragdoll API (Physics Mod 2.2.3 and above)
You can use it to add ragdoll physics to your mod entities. To make the physics work with your mod __USE__ the __ModelPart__ class for your custom entities and not create your own one, since I hook into this class.
You will need to create a __RagdollHook__ and add it via __RagdollMapper.addHook(RagdollHook)__. A ragdoll hook example can be found in this repo. 

## RagdollHook
***filterCuboidsFromEntities*** is to filter excessive cubes from the model that can get created through some feature overlays in Minecraft and would cause multiple cube drops of the same model.

***map*** is needed to create the connections between the cubes. If you are not sure which cubes should get connected you can get some info via __RagdollMapper.printModelParts(model)__.

Thanks to fabmax for creating a PhysX JNI Wrapper: https://github.com/fabmax/physx-jni

Mod page: https://minecraftphysicsmod.com
