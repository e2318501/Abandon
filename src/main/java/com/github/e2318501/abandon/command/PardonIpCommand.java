package com.github.e2318501.abandon.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import com.github.e2318501.abandon.Abandon;
import com.github.e2318501.abandon.util.Message;
import com.github.e2318501.abandon.util.IpChecker;
import com.github.e2318501.abandon.util.TabCompleteUtil;

import java.util.Collections;

public class PardonIpCommand extends Command implements TabExecutor {
    private final Abandon plugin;

    public PardonIpCommand(Abandon plugin) {
        super("apardon-ip", "abandon.command.pardon-ip");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            String ip = args[0];

            if (IpChecker.isIp(ip)) {
                if (plugin.getBannedIpManager().isBanned(ip)) {
                    plugin.getBannedIpManager().remove(ip);

                    sender.sendMessage(Message.UNBAN_IP_COMMAND.component(ip));
                } else {
                    sender.sendMessage(Message.IS_NOT_BANNED_IP.component());
                }
            } else {
                sender.sendMessage(Message.INVALID_IP.component());
            }
        } else if (args.length < 1) {
            sender.sendMessage(Message.INCOMPLETE_COMMAND.component());
        } else {
            sender.sendMessage(Message.INCORRECT_ARGUMENT.component());
        }
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return TabCompleteUtil.filter(plugin.getBannedIpManager().getIps(), args[0]);
        } else {
            return Collections.emptyList();
        }
    }
}
