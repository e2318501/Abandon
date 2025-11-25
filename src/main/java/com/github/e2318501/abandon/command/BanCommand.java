package com.github.e2318501.abandon.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import com.github.e2318501.abandon.Abandon;
import com.github.e2318501.abandon.util.Message;
import com.github.e2318501.abandon.util.TabCompleteUtil;

import java.util.*;

public class BanCommand extends Command implements TabExecutor {
    private final Abandon plugin;

    public BanCommand(Abandon plugin) {
        super("aban", "abandon.command.ban");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length >= 1) {
            String name = args[0];
            String reason = args.length == 1 ? Message.DEFAULT_BAN_REASON.text() : String.join(" ", Arrays.copyOfRange(args, 1, args.length));
            String source = sender instanceof ProxiedPlayer ? sender.getName() : Message.CONSOLE_SOURCE.text();

            Optional<UUID> uuid = plugin.getCachedPlayerManager().getUuid(name);
            if (uuid.isPresent()) {
                if (!plugin.getBannedPlayerManager().isBanned(name)) {
                    plugin.getBannedPlayerManager().add(uuid.get(), name, source, reason);

                    sender.sendMessage(Message.BAN_COMMAND.component(name, reason));

                    ProxiedPlayer proxiedPlayer = plugin.getProxy().getPlayer(name);
                    if (proxiedPlayer != null) {
                        proxiedPlayer.disconnect(Message.YOU_ARE_BANNED.component(reason));
                    }
                } else {
                    sender.sendMessage(Message.PLAYER_ALREADY_BANNED.component());
                }
            } else {
                sender.sendMessage(Message.PLAYER_NOT_FOUND.component());
            }
        } else {
            sender.sendMessage(Message.INCOMPLETE_COMMAND.component());
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return TabCompleteUtil.filter(plugin.getCachedPlayerManager().getNames(), args[0]);
        } else {
            return Collections.emptyList();
        }
    }
}
