package com.github.e2318501.abandon.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import com.github.e2318501.abandon.Abandon;
import com.github.e2318501.abandon.util.Message;
import com.github.e2318501.abandon.util.TabCompleteUtil;

import java.util.Collections;

public class PardonCommand extends Command implements TabExecutor {
    private final Abandon plugin;

    public PardonCommand(Abandon plugin) {
        super("apardon", "abandon.command.pardon");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            String name = args[0];

            if (plugin.getBannedPlayerManager().isBanned(name)) {
                plugin.getBannedPlayerManager().remove(name);

                sender.sendMessage(Message.UNBAN_COMMAND.component(name));
            } else {
                sender.sendMessage(Message.IS_NOT_BANNED.component());
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
            return TabCompleteUtil.filter(plugin.getBannedPlayerManager().getNames(), args[0]);
        } else {
            return Collections.emptyList();
        }
    }
}
