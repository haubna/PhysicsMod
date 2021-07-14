package net.diebuddies.physics.ragdoll;

import java.util.Iterator;
import java.util.List;

import net.diebuddies.physics.PhysicsEntity;
import net.diebuddies.physics.ragdoll.RagdollMapper.Counter;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.ArmorStandEntityModel;
import net.minecraft.client.render.entity.model.BatEntityModel;
import net.minecraft.client.render.entity.model.BeeEntityModel;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.ChickenEntityModel;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.DolphinEntityModel;
import net.minecraft.client.render.entity.model.DonkeyEntityModel;
import net.minecraft.client.render.entity.model.DrownedEntityModel;
import net.minecraft.client.render.entity.model.EndermiteEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.FoxEntityModel;
import net.minecraft.client.render.entity.model.GhastEntityModel;
import net.minecraft.client.render.entity.model.GuardianEntityModel;
import net.minecraft.client.render.entity.model.HoglinEntityModel;
import net.minecraft.client.render.entity.model.HorseEntityModel;
import net.minecraft.client.render.entity.model.IllagerEntityModel;
import net.minecraft.client.render.entity.model.IronGolemEntityModel;
import net.minecraft.client.render.entity.model.LlamaEntityModel;
import net.minecraft.client.render.entity.model.OcelotEntityModel;
import net.minecraft.client.render.entity.model.ParrotEntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.client.render.entity.model.RabbitEntityModel;
import net.minecraft.client.render.entity.model.RavagerEntityModel;
import net.minecraft.client.render.entity.model.SalmonEntityModel;
import net.minecraft.client.render.entity.model.SheepEntityModel;
import net.minecraft.client.render.entity.model.SilverfishEntityModel;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.render.entity.model.SnowGolemEntityModel;
import net.minecraft.client.render.entity.model.SpiderEntityModel;
import net.minecraft.client.render.entity.model.SquidEntityModel;
import net.minecraft.client.render.entity.model.StriderEntityModel;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.client.render.entity.model.WitherEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AbstractDonkeyEntity;

public class ExampleRagdollHook implements RagdollHook {

	@Override
	public void map(RagdollPX ragdoll, Entity entity, EntityModel model) {
		if (model instanceof BipedEntityModel) {
			AnimalModel animal = (AnimalModel) model;
			// basic ragdoll for humans/zombies
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			}
			
			int headOffset = 0;
			int bodyOffset = counter.count;
			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int rightArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int hatOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(hatOffset, headOffset, true);
			
			if (model instanceof PlayerEntityModel) {
				try {
					int leftPantsOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int rightPantsOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int leftSleeveOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int rightSleeveOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int jacketOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					ragdoll.addConnection(leftPantsOffset, leftLegOffset, true);
					ragdoll.addConnection(rightPantsOffset, rightLegOffset, true);
					ragdoll.addConnection(leftSleeveOffset, leftArmOffset, true);
					ragdoll.addConnection(rightSleeveOffset, rightArmOffset, true);
					ragdoll.addConnection(jacketOffset, bodyOffset, true);
				} catch (Exception e) {}
			} else if (model instanceof ArmorStandEntityModel) {
				try {
					int rightBodyStickOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int leftBodyStickOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int shoulderStickOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					int basePlateOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
					ragdoll.addConnection(rightBodyStickOffset, bodyOffset, true);
					ragdoll.addConnection(leftBodyStickOffset, bodyOffset, true);
					ragdoll.addConnection(shoulderStickOffset, bodyOffset, true);
				} catch (Exception e) {}
			}
			
			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
		} else if (model instanceof QuadrupedEntityModel) {
			AnimalModel animal = (AnimalModel) model;
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			}
			
			int headOffset = 0;
			int bodyOffset = counter.count;
			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int rightArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			
			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
		} else if (model instanceof ChickenEntityModel) { 
			AnimalModel animal = (AnimalModel) model;
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			int headOffset = 0;
			int beakOffset = RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			int wattleOffset = RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			}

			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int bodyOffset = counter.count;
			int rightLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightWingOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftWingOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			ragdoll.addConnection(beakOffset, headOffset, true);
			ragdoll.addConnection(wattleOffset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(rightWingOffset, bodyOffset);
			ragdoll.addConnection(leftWingOffset, bodyOffset);
			
			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
		} else if (model instanceof SquidEntityModel) {
			ragdoll.addConnection(0, 4);
			ragdoll.addConnection(1, 4);
			ragdoll.addConnection(2, 4);
			ragdoll.addConnection(3, 4);
			ragdoll.addConnection(5, 4);
			ragdoll.addConnection(6, 4);
			ragdoll.addConnection(7, 4);
			ragdoll.addConnection(8, 4);
			
			RagdollMapper.getCuboids(ragdoll, ((SinglePartEntityModel) model).getPart(), new Counter());
		} else if (model instanceof CreeperEntityModel) {
			int headOffset = 0;
			int rightArmOffset = 1;
			int rightLegOffset = 2;
			int leftLegOffset = 3;
			int bodyOffset = 4;
			int leftArmOffset = 5;

			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			
			RagdollMapper.getCuboids(ragdoll, ((SinglePartEntityModel) model).getPart(), new Counter());
		} else if (model instanceof DolphinEntityModel) {
			int bodyOffset = 0;
			int headOffset = 1;
			int noseOffset = 2;
			int leftFinOffset = 3;
			int rightFinOffset = 4;
			int tailOffset = 5;
			int tailFinOffset = 6;
			int backFinOffset = 7;
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(leftFinOffset, bodyOffset, true);
			ragdoll.addConnection(rightFinOffset, bodyOffset, true);
			ragdoll.addConnection(backFinOffset, bodyOffset, true);
			ragdoll.addConnection(tailOffset, bodyOffset);
			ragdoll.addConnection(tailFinOffset, tailOffset);
			
			RagdollMapper.getCuboids(ragdoll, ((SinglePartEntityModel) model).getPart(), new Counter());
		} else if (model instanceof GhastEntityModel) {
			ragdoll.addConnection(0, 5);
			ragdoll.addConnection(1, 5);
			ragdoll.addConnection(2, 5);
			ragdoll.addConnection(3, 5);
			ragdoll.addConnection(4, 5);
			ragdoll.addConnection(6, 5);
			ragdoll.addConnection(7, 5);
			ragdoll.addConnection(8, 5);
			ragdoll.addConnection(9, 5);
		} else if (model instanceof IronGolemEntityModel) {
			int headOffset = 0;
			int noseOffset = 1;
			int rightArmOffset = 2;
			int leftLegOffset = 3;
			int leftArmOffset = 4;
			int rightLegOffset = 5;
			int bodyOffset = 6;
			int lowerBodyOffset = 7;
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, lowerBodyOffset);
			ragdoll.addConnection(rightLegOffset, lowerBodyOffset);
			ragdoll.addConnection(lowerBodyOffset, bodyOffset);
		} else if (model instanceof SpiderEntityModel) {
			// TODO cave spider pivot points are wrong (strider babies as well)
			// probably due to scaling, when fixed Ghast should also
			// work since he has scale issues as well
			int headOffset = 0;
			int rightFrontLegOffset = 1;
			int rightHindLegOffset = 2;
			int leftMiddleFrontLegOffset = 3;
			int body0Offset = 4;
			int body1Offset = 5;
			int leftHindLegOffset = 6;
			int rightMiddleHindLegOffset = 7;
			int rightMiddleFrontLegOffset = 8;
			int leftMiddleHindLegOffset = 9;
			int leftFrontLegOffset = 10;
			
			ragdoll.addConnection(headOffset, body0Offset);
			ragdoll.addConnection(body1Offset, body0Offset);
			ragdoll.addConnection(rightFrontLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(rightHindLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(leftMiddleFrontLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(leftHindLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(rightMiddleHindLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(rightMiddleFrontLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(leftMiddleHindLegOffset, body0Offset).stopCollision = true;
			ragdoll.addConnection(leftFrontLegOffset, body0Offset).stopCollision = true;
		} else if (model instanceof SnowGolemEntityModel) {
			int headOffset = 0;
			int rightArmOffset = 1;
			int upperBodyOffset = 2;
			int leftArmOffset = 3;
			int lowerBodyOffset = 4;

			ragdoll.addConnection(headOffset, upperBodyOffset);
			ragdoll.addConnection(rightArmOffset, upperBodyOffset);
			ragdoll.addConnection(leftArmOffset, upperBodyOffset);
			ragdoll.addConnection(lowerBodyOffset, upperBodyOffset);
		} else if (model instanceof GuardianEntityModel) {
			int headOffset = 0;
			int spike0 = 21;
			int spike1 = 5;
			int spike2 = 6;
			int spike3 = 7;
			int spike4 = 8;
			int spike5 = 9;
			int spike6 = 10;
			int spike7 = 16;
			int spike8 = 17;
			int spike9 = 18;
			int spike10 = 19;
			int spike11 = 20;
			int eye = 11;
			int tail0 = 12;
			int tail1 = 13;
			int tail2 = 14;
			int tail3 = 15;

			// stretches the tail too much
//			ragdoll.addConnection(tail0, headOffset);
//			ragdoll.addConnection(tail1, tail0);
//			ragdoll.addConnection(tail2, tail1);
//			ragdoll.addConnection(tail3, tail2);
			ragdoll.addConnection(tail0, headOffset, true);
			ragdoll.addConnection(tail1, headOffset, true);
			ragdoll.addConnection(tail2, headOffset, true);
			ragdoll.addConnection(tail3, headOffset, true);
			
			ragdoll.addConnection(spike0, headOffset, true);
			ragdoll.addConnection(spike1, headOffset, true);
			ragdoll.addConnection(spike2, headOffset, true);
			ragdoll.addConnection(spike3, headOffset, true);
			ragdoll.addConnection(spike4, headOffset, true);
			ragdoll.addConnection(spike5, headOffset, true);
			ragdoll.addConnection(spike6, headOffset, true);
			ragdoll.addConnection(spike7, headOffset, true);
			ragdoll.addConnection(spike8, headOffset, true);
			ragdoll.addConnection(spike9, headOffset, true);
			ragdoll.addConnection(spike10, headOffset, true);
			ragdoll.addConnection(spike11, headOffset, true);
			ragdoll.addConnection(headOffset + 1, headOffset, true);
			ragdoll.addConnection(headOffset + 2, headOffset, true);
			ragdoll.addConnection(headOffset + 3, headOffset, true);
			ragdoll.addConnection(headOffset + 4, headOffset, true);
			ragdoll.addConnection(eye, headOffset, true);
		} else if (model instanceof VillagerResemblingModel) {
			int headOffset = 0;
			int noseOffset = 1;
			int hatOffset = 2;
			int hatRimOffset = 3;
			int leftLegOffset = 4;
			int rightLegOffset = 5;
			int armsOffset = 6;
			int bodyOffset = 9;
			int jacketOffset = 10;

			ragdoll.addConnection(hatOffset, headOffset, true);
			ragdoll.addConnection(hatRimOffset, headOffset, true);
			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(jacketOffset, bodyOffset);
			ragdoll.addConnection(armsOffset, bodyOffset, true);
			ragdoll.addConnection(armsOffset + 1, bodyOffset, true);
			ragdoll.addConnection(armsOffset + 2, bodyOffset, true);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(armsOffset, bodyOffset);
		} else if (model instanceof IllagerEntityModel) {
			int headOffset = 0;
			int noseOffset = 1;
			int leftLegOffset = 2;
			int rightArmOffset = 3;
			int rightLegOffset = 4;
			int leftArmOffset = 5;
			int bodyOffset = 6;

			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(bodyOffset + 1, bodyOffset, true);
		} else if (model instanceof StriderEntityModel) {
			int leftLegOffset = 0;
			int rightLegOffset = 1;
			int bodyOffset = 2;

			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(3, bodyOffset, true);
			ragdoll.addConnection(4, bodyOffset, true);
			ragdoll.addConnection(5, bodyOffset, true);
			ragdoll.addConnection(6, bodyOffset, true);
			ragdoll.addConnection(7, bodyOffset, true);
			ragdoll.addConnection(8, bodyOffset, true);

			ragdoll.bodies.get(bodyOffset).backfaceCulling = false;
		} else if (model instanceof RavagerEntityModel) {
			int rightFrontLegOffset = 0;
			int rightHindLegOffset = 1;
			int leftHindLegOffset = 2;
			int neckOffset = 3;
			int headOffset = 4;
			int headChildOffset = 5;
			int rightHornOffset = 6;
			int mouthOffset = 7;
			int leftHornOffset = 8;
			int bodyOffset = 9;
			int bodyChildOffset = 10;
			int leftFrontLegOffset = 11;

			ragdoll.addConnection(bodyChildOffset, bodyOffset);
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
			ragdoll.addConnection(rightHindLegOffset, bodyChildOffset);
			ragdoll.addConnection(leftHindLegOffset, bodyChildOffset);
			ragdoll.addConnection(neckOffset, bodyOffset);
			ragdoll.addConnection(headOffset, neckOffset);
			ragdoll.addConnection(mouthOffset, headOffset, true);
			ragdoll.addConnection(rightHornOffset, headOffset, true);
			ragdoll.addConnection(leftHornOffset, headOffset, true);
			ragdoll.addConnection(headChildOffset, headOffset, true);
		} else if (model instanceof BatEntityModel) {
			int headOffset = 0;
			int rightEarOffset = 1;
			int leftEarOffset = 2;
			int bodyOffset = 3;
			int bodyChildOffset = 4;
			int rightWingOffset = 5;
			int rightWingTipOffset = 6;
			int leftWingOffset = 7;
			int leftWingTipOffset = 8;

			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(bodyChildOffset, bodyOffset, true);
			ragdoll.addConnection(rightEarOffset, headOffset, true);
			ragdoll.addConnection(leftEarOffset, headOffset, true);
			
			ragdoll.addConnection(rightWingTipOffset, rightWingOffset, true);
			ragdoll.addConnection(rightWingOffset, bodyOffset);
			ragdoll.addConnection(leftWingTipOffset, leftWingOffset, true);
			ragdoll.addConnection(leftWingOffset, bodyOffset);
		} else if (model instanceof BeeEntityModel) {
			// TODO some vertices in cuboid class are mirrored/flipped that I don't encount for yet
			// that's why the bee wings are messed up
			int frontLegsOffset = 0;
			int rightWingOffset = 1;
			int leftWingOffset = 2;
			int middleLegsOffset = 3;
			int leftAntennaOffset = 4;
			int rightAntennaOffset = 5;
			int stingerOffset = 6;
			int bodyOffset = 7;
			int backLegsOffset = 8;

			ragdoll.addConnection(frontLegsOffset, bodyOffset, true);
			ragdoll.addConnection(rightWingOffset, bodyOffset, true);
			ragdoll.addConnection(leftWingOffset, bodyOffset, true);
			ragdoll.addConnection(middleLegsOffset, bodyOffset, true);
			ragdoll.addConnection(rightAntennaOffset, bodyOffset, true);
			ragdoll.addConnection(stingerOffset, bodyOffset, true);
			ragdoll.addConnection(leftAntennaOffset, bodyOffset, true);
			ragdoll.addConnection(backLegsOffset, bodyOffset, true);
		} else if (model instanceof RabbitEntityModel) {
			int headOffset;
			int noseOffset;
			int rightFrontLegOffset;
			int rightHindFootOffset;
			int tailOffset;
			int leftHaunchOffset;
			int rightHaunchOffset;
			int bodyOffset;
			int rightEarOffset;
			int leftFrontLegOffset;
			int leftHindFootOffset;
			int leftEarOffset;
			
			if (model.child) {
				headOffset = 0;
				leftEarOffset = 1;
				rightEarOffset = 2;
				noseOffset = 3;
				leftHindFootOffset = 4;
				rightHindFootOffset = 5;
				leftHaunchOffset = 6;
				rightHaunchOffset = 7;
				bodyOffset = 8;
				leftFrontLegOffset = 9;
				rightFrontLegOffset = 10;
				tailOffset = 11;
			} else {
				leftHindFootOffset = 0;
				rightHindFootOffset = 1;
				leftHaunchOffset = 2;
				rightHaunchOffset = 3;
				bodyOffset = 4;
				leftFrontLegOffset = 5;
				rightFrontLegOffset = 6;
				headOffset = 7;
				rightEarOffset = 8;
				leftEarOffset = 9;
				tailOffset = 10;
				noseOffset = 11;
			}

			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(leftEarOffset, headOffset);
			ragdoll.addConnection(rightEarOffset, headOffset);
			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
			ragdoll.addConnection(tailOffset, bodyOffset, true);
			ragdoll.addConnection(rightHaunchOffset, bodyOffset);
			ragdoll.addConnection(leftHaunchOffset, bodyOffset);
			ragdoll.addConnection(rightHindFootOffset, rightHaunchOffset);
			ragdoll.addConnection(leftHindFootOffset, leftHaunchOffset);
		} else if (model instanceof WitherEntityModel) {
			int shouldersOffset = 0;
			int ribcageOffset = 1;
			int tailOffset = 5;
			int leftHeadOffset = 6;
			int rightHeadOffset = 7;
			int centerHeadOffset = 8;

			ragdoll.addConnection(tailOffset, ribcageOffset);
			ragdoll.addConnection(shouldersOffset, ribcageOffset, true);
			ragdoll.addConnection(leftHeadOffset, ribcageOffset);
			ragdoll.addConnection(rightHeadOffset, ribcageOffset);
			ragdoll.addConnection(centerHeadOffset, ribcageOffset);
			
			RagdollMapper.getCuboids(ragdoll, ((SinglePartEntityModel) model).getPart(), new Counter());
		} else if (model instanceof OcelotEntityModel) {
			AnimalModel animal = (AnimalModel) model;
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			}
			
			int headOffset = 0;
			int bodyOffset = counter.count;
			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int rightArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int upperTailOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int lowerTailOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(upperTailOffset, bodyOffset);
			ragdoll.addConnection(lowerTailOffset, upperTailOffset);
			
			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
		} else if (model instanceof FoxEntityModel) {
			int headOffset = 0;
			int noseOffset = 1;
			int rightEarOffset = 2;
			int leftEarOffset = 3;
			int bodyOffset = 4;
			int tailOffset = 5;
			int leg1Offset = 6;
			int leg2Offset = 7;
			int leg3Offset = 8;
			int leg4Offset = 9;

			ragdoll.addConnection(noseOffset, headOffset, true);
			ragdoll.addConnection(rightEarOffset, headOffset, true);
			ragdoll.addConnection(leftEarOffset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(tailOffset, bodyOffset);
			ragdoll.addConnection(leg1Offset, bodyOffset);
			ragdoll.addConnection(leg2Offset, bodyOffset);
			ragdoll.addConnection(leg3Offset, bodyOffset);
			ragdoll.addConnection(leg4Offset, bodyOffset);
		} else if (model instanceof SilverfishEntityModel) {
			int segment2Offset = 0;
			int segment1Offset = 1;
			int segment0Offset = 2;
			// big one
			int layer0Offset = 3;
			// small one
			int layer1Offset = 4;
			// medium one
			int layer2Offset = 5;
			int segment6Offset = 6;
			int segment5Offset = 7;
			int segment4Offset = 8;
			int segment3Offset = 9;

			ragdoll.addConnection(segment0Offset, segment1Offset);
			ragdoll.addConnection(segment1Offset, segment2Offset);
			ragdoll.addConnection(segment2Offset, segment3Offset);
			ragdoll.addConnection(segment3Offset, segment4Offset);
			ragdoll.addConnection(segment4Offset, segment5Offset);
			ragdoll.addConnection(segment5Offset, segment6Offset);

			ragdoll.addConnection(layer0Offset, segment2Offset, true);
			ragdoll.addConnection(layer1Offset, segment4Offset, true);
			ragdoll.addConnection(layer2Offset, segment1Offset, true);
		} else if (model instanceof EndermiteEntityModel) {
			int segment2Offset = 0;
			int segment1Offset = 1;
			int segment0Offset = 2;
			int segment3Offset = 3;

			ragdoll.addConnection(segment0Offset, segment1Offset);
			ragdoll.addConnection(segment1Offset, segment2Offset);
			ragdoll.addConnection(segment2Offset, segment3Offset);
		} else if (model instanceof ParrotEntityModel) {
			int headOffset = 0;
			int beak1Offset = 1;
			int beak2Offset = 2;
			int featherOffset = 3;
			int head2Offset = 4;
			int leftLegOffset = 5;
			int rightWingOffset = 6;
			int rightLegOffset = 7;
			int tailOffset = 8;
			int leftWingOffset = 9;
			int bodyOffset = 10;

			ragdoll.addConnection(beak1Offset, headOffset, true);
			ragdoll.addConnection(beak2Offset, headOffset, true);
			ragdoll.addConnection(featherOffset, headOffset, true);
			ragdoll.addConnection(head2Offset, headOffset, true);
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(rightWingOffset, bodyOffset);
			ragdoll.addConnection(leftWingOffset, bodyOffset);
			ragdoll.addConnection(tailOffset, bodyOffset);
		} else if (model instanceof HorseEntityModel) {
			AnimalModel animal = (AnimalModel) model;
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			}
			
			boolean hasSaddle = ((ModelPart) animal.getHeadParts().iterator().next()).getChild("left_saddle_mouth").visible;
			int neckOffset = 0;
			int bodyOffset = counter.count;
			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int rightHindLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftHindLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightFrontLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftFrontLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			if (model.child) {
				rightHindLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
				leftHindLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
				rightFrontLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
				leftFrontLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
			
			ragdoll.addConnection(neckOffset, bodyOffset);
			ragdoll.addConnection(rightHindLegOffset, bodyOffset);
			ragdoll.addConnection(leftHindLegOffset, bodyOffset);
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
			
			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
			
			int leftEarOffset = 2;
			int rightEarOffset = 3;
			int headOffset = 1;
			
			int mane = 4;
			int upperMouth = 5;
			int tail = 7;
			
			if (hasSaddle) {
				int leftSaddleMouthOffset = 4;
				int mouthSaddleWrapOffset = 5;
				// right saddle line and left saddle line are never used
				int rightSaddleLineOffset = 6;
				int rightSaddleMouthOffset = 7;
				int leftSaddleLineOffset = 8;
				int saddleOffset = 11;
				int headSaddleOffset = 9;
				upperMouth = 8;
				mane = 6;
				tail = 12;
				
				if (model instanceof DonkeyEntityModel) {
					boolean hasChests = ((AbstractDonkeyEntity) entity).hasChest();
					
					if (hasChests) {
						saddleOffset = 12;
						tail = 13;
					}
					
					// just ignore chest positions to make them pop off the model
//					int leftChest = 14;
//					int rightChest = 11;
				}

				ragdoll.addConnection(leftSaddleMouthOffset, neckOffset, true);
				ragdoll.addConnection(mouthSaddleWrapOffset, neckOffset, true);
				ragdoll.addConnection(rightSaddleMouthOffset, neckOffset, true);
				ragdoll.addConnection(headSaddleOffset, neckOffset, true);
				ragdoll.addConnection(saddleOffset, bodyOffset, true);
			} else {
				if (model instanceof DonkeyEntityModel) {
					boolean hasChests = ((AbstractDonkeyEntity) entity).hasChest();
					
					if (hasChests) {
						tail = 8;
					}
				}
			}
			
			ragdoll.addConnection(headOffset, neckOffset, true);
			ragdoll.addConnection(leftEarOffset, neckOffset, true);
			ragdoll.addConnection(rightEarOffset, neckOffset, true);
			
			ragdoll.addConnection(tail, bodyOffset);
			ragdoll.addConnection(mane, neckOffset, true);
			ragdoll.addConnection(upperMouth, neckOffset, true);
			
			// for testing
//			/summon horse ~ ~ ~ {Tame:1,SaddleItem:{id:saddle,Count:1},ArmorItem:{id:iron_horse_armor,Count:1}}
//			/summon horse ~ ~ ~ {Tame:1,SaddleItem:{id:saddle,Count:1}}
//			/summon donkey ~ ~ ~ {Tame:1,SaddleItem:{id:saddle,Count:1},ChestedHorse:1}
		} else if (model instanceof LlamaEntityModel) {
			int headOffset = 0;
			int neckOffset = 1;
			int earLeftOffset = 2;
			int earRightOffset = 3;
			int bodyOffset = 4;
			int rightFrontLegOffset = 5;
			int rightHindLegOffset = 6;
			int leftHindLegOffset = 7;
			int leftFrontLegOffset = 8;
			
			// don't use chest positions to let them pop off the model
			int rightChestOffset = 9;
			int leftChestOffset = 10;
			
			ragdoll.addConnection(headOffset, neckOffset, true);
			ragdoll.addConnection(earLeftOffset, neckOffset, true);
			ragdoll.addConnection(earRightOffset, neckOffset, true);
			
			ragdoll.addConnection(neckOffset, bodyOffset);
			ragdoll.addConnection(rightFrontLegOffset, bodyOffset);
			ragdoll.addConnection(rightHindLegOffset, bodyOffset);
			ragdoll.addConnection(leftHindLegOffset, bodyOffset);
			ragdoll.addConnection(leftFrontLegOffset, bodyOffset);
		} else if (model instanceof HoglinEntityModel) {
			AnimalModel animal = (AnimalModel) model;
			Iterator<ModelPart> head = animal.getHeadParts().iterator();
			Counter counter = new Counter();
			
			while (head.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, head.next(), counter);
			}
			
			int headOffset = 0;
			int bodyOffset = counter.count;
			Iterator<ModelPart> body = animal.getBodyParts().iterator();
			int rightArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftArmOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int rightLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			int leftLegOffset = RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			
			ragdoll.addConnection(headOffset, bodyOffset);
			ragdoll.addConnection(rightArmOffset, bodyOffset);
			ragdoll.addConnection(leftArmOffset, bodyOffset);
			ragdoll.addConnection(rightLegOffset, bodyOffset);
			ragdoll.addConnection(leftLegOffset, bodyOffset);
			
			int rightHornOffset = 1;
			int leftHornOffset = 2;
			int rightEarOffset = 3;
			int leftEarOffset = 4;
			int maneOffset = 6;
			
			ragdoll.addConnection(rightHornOffset, headOffset, true);
			ragdoll.addConnection(leftHornOffset, headOffset, true);
			ragdoll.addConnection(rightEarOffset, headOffset, true);
			ragdoll.addConnection(leftEarOffset, headOffset, true);
			ragdoll.addConnection(maneOffset, bodyOffset, true);
			
			while (body.hasNext()) {
				RagdollMapper.getCuboids(ragdoll, body.next(), counter);
			}
		} else if (model instanceof SalmonEntityModel) {
			int headOffset = 0;
			int leftFinOffset = 1;
			int bodyBackOffset = 2;
			int topBackFinOffset = 3;
			int backFinOffset = 4;
			int rightFinOffset = 5;
			int bodyFrontOffset = 6;
			int topFrontFinOffset = 7;
			
			ragdoll.addConnection(headOffset, bodyFrontOffset);
			ragdoll.addConnection(bodyBackOffset, bodyFrontOffset);
			
			ragdoll.addConnection(topFrontFinOffset, bodyFrontOffset, true);
			ragdoll.addConnection(leftFinOffset, bodyFrontOffset, true);
			ragdoll.addConnection(rightFinOffset, bodyFrontOffset, true);
			ragdoll.addConnection(topBackFinOffset, bodyBackOffset, true);
			ragdoll.addConnection(backFinOffset, bodyBackOffset, true);
		}
	}

	@Override
	public void filterCuboidsFromEntities(List<PhysicsEntity> blockifiedEntity, Entity entity, EntityModel model) {
		if (model instanceof IronGolemEntityModel) {
			while (blockifiedEntity.size() > 8) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof SpiderEntityModel) {
			while (blockifiedEntity.size() > 11) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof VillagerResemblingModel) {
			while (blockifiedEntity.size() > 11) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof StriderEntityModel) {
			while (blockifiedEntity.size() > 9) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof IllagerEntityModel) {
			while (blockifiedEntity.size() > 8) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof WitherEntityModel) {
			while (blockifiedEntity.size() > 9) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof SheepEntityModel) {
			while (blockifiedEntity.size() > 6) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof DrownedEntityModel) {
			while (blockifiedEntity.size() > 7) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof HorseEntityModel) {
			while (blockifiedEntity.size() > RagdollMapper.countModelParts(model)) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		} else if (model instanceof LlamaEntityModel) {
			int total = 9;
			
			if (!((AbstractDonkeyEntity) entity).isBaby() && ((AbstractDonkeyEntity) entity).hasChest()) {
				total = 11;
			}
			
			while (blockifiedEntity.size() > total) {
				blockifiedEntity.remove(blockifiedEntity.size() - 1);
			}
		}
	}

}
