PermClasses
===========
Flexible class system for Bukkit.

What is PermClasses?
-----------

**PermClasses** is a Bukkit plugin intended to provide a class system without being limited to a specific plugin's implementation of the concept. 

PermClasses allows _extremely_ customizable classes to be created. The plugin relies on two things:

* Permissions
* Commands

By combining permissions and commands, one can create the most fine-tuned game experience for a player. 

PermClasses uses **permissions** to determine the actions of a player with a certain class. The player is added to a permission group in a Vault-supported plugin and inherits all of the properties of the group. 

PermClasses also uses **commands** to give a player traits. An example of this would be a class that teleports the player to another world, allowing the player to do more. 

These two can also be **combined**. Say you want a mage class to use a Heroes class. You would add the corresponding permission for the Heroes class and the command to actually give the class to the player. To unbind, remove the class.

Installation
-----------

Simply drag-and-drop the "PermClasses.jar" file into your server's plugin directory.

Configuration
------------

The configuration of PermClasses is stored in the **plugins/PermClasses/config.yml** file of your server.

Classes are configured as follows:

    classes:
        "Tier Name":
            "Type Name":
                "Class Name":
                    bind: []
                    unbind: []

As many tiers/types/classes as you want can be added to their parent. Bind and unbind should be formatted as above if you do not want to use commands. If you do, however, you should use this:

    bind:
    - "/playercommand 1"
    - "consolecommand blah"

Commands prefixed with a "/" will be sent by the player. Commands not prefixed will be sent by the console.

### Adding Permissions to Classes

PermClasses depends on 3rd party plugins to do a lot of its work. Personally, I recommend the unbelievably awesome [bPermissions](http://dev.bukkit.org/server-mods/bpermissions/), but you can use any Permissions plugin supported by [Vault](http://dev.bukkit.org/server-mods/vault/) that also supports groups.

Permission groups must be named with a prefix of `_pcl_`. Their names must also be all lowercase, numbers, and hyphens. For example, if you had a class named "Warrior", your permission group would be named `_pcl_warrior`.

Commands
-----------
### Admin
* `/pcl set <player> <class>` - Sets the class of `player` to `class`.
* `/pcl rm <player> <class type>` - Removes the class of `player` that corresponds with `class type`, if any.
* `/pcl reset <player>` - Resets all classes of `player` to nothing.
* `/pcl reload` - Reloads all configuration.

Permissions
-----------
### Player
* `pcl.use.*` - Allows the player to use any class.

### Admin
* `pcl.admin` - Grants all admin nodes as listed below, and also grants `pcl.use.*`.
* `pcl.admin.set` - Allows use of the `/pcl set <player> <class>` command.
* `pcl.admin.rm` - Allows use of the `/pcl rm <player> <class type>` command.
* `pcl.admin.reset` - Allows use of the `/pcl reset <player>` command.
* `pcl.admin.reload` - Allows use of the `/pcl reload` command.

Need help?
-----------

The best, **most certain** way to get help is to hop onto **irc.esper.net** in the **#AlbireoX** channel. I will be there 99% of the time.

The second way is to reply on the plugin thread.

There is no third way. Don't comment on BukkitDev. I won't look at it.

API
-----------

Hooking into Permissions requires nothing more than SpoutAPI! Simply use ```subject.hasPermission(permissionNode);```

Credits
-----------

[![simplyianm/AlbireoX](http://www.gravatar.com/avatar/9c5c3b395c30ab444a71bd0d52ddd73c.png "simplyianm")](http://simplyian.com/)

[simplyianm](http://simplyian.com/) AKA [AlbireoX](http://forums.spout.org/members/albireox.482/)

Donate (I have no job!)
------------
[![Donate to an unemployed developer.](https://www.paypalobjects.com/en_US/i/btn/btn_donate_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=ZFB2YYTVAH6PG)

License
------------
PermClasses is licensed under the [GNU AGPL V3 License](http://www.gnu.org/licenses/agpl.html). In a nutshell, if you fork my code, you have to provide source for it even if you're not distributing it.

Permissions is [open source](http://github.com/simplyianm/PermClasses) | [Report an issue](http://github.com/simplyianm/PermClasses/issues)