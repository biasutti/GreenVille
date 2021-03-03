package ch.biasutti.greenville.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (p.getName().equals("Lord_of_Dankness")) {
            e.setMessage(e.getMessage() + ChatColor.BLACK + ChatColor.BOLD + ChatColor.UNDERLINE + " NOHOMO");
        }
    }

}
