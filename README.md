# Abandon

Abandon is a simple punishment plugin for BungeeCord, which has similar commands
to the default `/ban` (and so on) commands in Minecraft.

## Features

- Rich tab completion
- Simple data management (e.g. uses `banned-players.json`)

## Usage

### Commands

| Command                            | Permission                 | Description                                 |
| ---------------------------------- | -------------------------- | ------------------------------------------- |
| `/aban <player> [<reason>]`        | `abandon.command.ban`       | Add the player to the player blacklist      |
| `/aban-ip <player\|ip> [<reason>]` | `abandon.command.ban-ip`    | Add the IP address to the IP blacklist      |
| `/abanlist [<players\|ips>]`       | `abandon.command.banlist`   | Display the blacklist                       |
| `/apardon <player>`                | `abandon.command.pardon`    | Remove the player from the player blacklist |
| `/apardon-ip <ip>`                 | `abandon.command.pardon-ip` | Remove the IP address from the IP blacklist |

### Data Importing

You can copy the following files from your Minecraft server and apply the data
to this plugin:

- `banned-players.json`
- `banned-ips.json`
- `usercache.json` (used for tab completion)

Make sure that the proxy is stopping while copying.
