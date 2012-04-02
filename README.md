PermClasses
===========

What is PermClasses?
-----------

**PermClasses** is a Bukkit plugin intended to provide a class system without sacrificing content. 

PermClasses allows _extremely_ customizable classes to be created. The plugin relies on two things:

* Permissions
* Commands

By combining permissions and commands, one can create the most fine-tuned game experience for a player. 

PermClasses uses **permissions** to determine the actions of a player with a certain class. The player is added to a permission group in a Vault-supported plugin and inherits all of the properties of the group. PermClasses also uses **commands** to give a player traits. An example of this would be a class that teleports the player to another world, allowing the player to do more. These two can also be combined. Say you want a mage class to use a Heroes class. You would add the corresponding permission for the Heroes class and the command to actually give the class to the player. To unbind, remove the class.

Installation
-----------

Simply drag-and-drop the "PermClasses.jar" file into your server's plugin directory.

### Configuration

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