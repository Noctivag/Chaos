# Chaos Mod

A server-side only Fabric mod for Minecraft 1.21.4 that adds random chaos events to your multiplayer server!

## Features

This mod is a port of the Highland chaos mod for multiplayer, designed to run **only on the server** - clients don't need to install it!

### Chaos Events

The mod randomly triggers various chaos events on the server:

- **Weather Chaos**: Randomly changes weather between clear, rain, and thunderstorms
- **Mob Spawn Chaos**: Spawns random mobs near players (both friendly and hostile)
- **Player Effect Chaos**: Applies random potion effects to players
- **Item Drop Chaos**: Drops random items near players
- **Explosion Chaos**: Creates explosions near players (not on them!)
- **Lightning Chaos**: Strikes lightning near players

### Configuration

Default settings (configurable in `ChaosConfig.java`):

- Event interval: Every 30 seconds (with random variance)
- All event types enabled by default
- Moderate intensities for explosions and mob spawns

## Installation

### Requirements

- Minecraft 1.21.4
- Fabric Loader 0.16.9 or higher
- Fabric API 0.110.5 or higher
- Java 21 or higher

### Server Installation

1. Download the mod JAR file
2. Place it in your server's `mods` folder
3. Make sure Fabric API is also in the `mods` folder
4. Start the server

**That's it!** Players can connect without installing anything on their client.

## Building from Source

```bash
./gradlew build
```

The compiled JAR will be in `build/libs/`.

## Server-Side Only

This mod uses the `"environment": "server"` setting in `fabric.mod.json`, which means:

- ✅ Only needs to be installed on the server
- ✅ Clients can connect without the mod
- ✅ No client-side code or assets
- ✅ All chaos events are server-controlled

## License

MIT License - See LICENSE file for details

## Credits

Port of the Highland chaos mod for multiplayer compatibility.
