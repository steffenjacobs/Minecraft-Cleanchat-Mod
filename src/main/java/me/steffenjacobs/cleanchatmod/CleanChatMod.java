package me.steffenjacobs.cleanchatmod;

import me.steffenjacobs.cleanchatmod.config.CleanChatConfiguration;
import me.steffenjacobs.cleanchatmod.config.ConfigManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod(value = "cleanchatmod")
@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class CleanChatMod {
    private static final UUID EMPTY_UUID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    private final CleanChatConfiguration configuration;

    public CleanChatMod() {
        MinecraftForge.EVENT_BUS.register(this);
        configuration = new ConfigManager().load();
    }


    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof PlayerEntity && event.getEntity() == Minecraft.getInstance().player) {
            Minecraft.getInstance().player.sendMessage(new StringTextComponent("[CLEANCHAT MOD]: "
                    + (configuration.isIgnoreVoting() ? " Ignoring voting messages. Don't forget to vote!" :
                    "Message filter not enabled.")), EMPTY_UUID);
        }
    }

    @SubscribeEvent
    public void chatMessageReceived(ClientChatReceivedEvent event) {
        if(configuration.isIgnoreVoting() && event.getMessage().getString().startsWith(configuration.getPrefixVoting())){
            event.setCanceled(true);
        }
    }
}
