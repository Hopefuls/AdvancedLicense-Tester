
# AdvancedLicense-Tester
A simple-to-use tool to test your [AdvancedLicense Licensing System by Leoko](https://www.spigotmc.org/resources/advancedlicense.20823/)

## Usage
This Tool allows you to do simple request in order to check the functionality of your AdvancedLicense Licensing System without the hassle to either create a new Plugin purely for testing, or adding the validation into your existing plugin.

***IMPORTANT: Your License Panel should be accessible to a point where you are able to create licenses.***
 - Download the latest release from [here](https://github.com/Hopefuls/AdvancedLicense-Tester/releases).
 - Simply run the program by either using `java -jar AdvancedLicense-Tester.jar` or executing it through the desktop itself by double-clicking.
 - a Window like this should open now (may vary between versions)
![The Dialog itself](https://yes.is-inside.me/wYAkt9FB.png)
 - Fill out the corresponding fields
	 - Verification URL from your AdvancedLicense Licensing Panel (for example `https://example.com/licensing/verify.php`)
	 - License Key with your freshly generated Key from your License Panel (under *add Licenses*)
	 - Debug Mode Output (optional) if you want to receive more Advanced output such as request informations.
 - Press *OK*
 - You should get something like this in return
 ![A Valid Key! hurray!](https://yes.is-inside.me/QksCQU4Q.png)
The results can of course vary between the corresponding Validation Types shown on the [Spigot Page](https://www.spigotmc.org/resources/advancedlicense.20823/)
![Server Valid! Atleast that works](https://yes.is-inside.me/bd7OaB6l.png)![Oh dang, time to recheck!](https://yes.is-inside.me/GRAsSsf0.png)

##
## Currently known Bugs (well not really bugs)

 - Scaling the UI causes the whole thing to break completely (will work on that)
 - Doesn't show up in Taskbar (because i used JDialog instead of JFrame, will change that)
 /\ also causes the Window to not really being accessible anymore after changing, temporary solution is to alt+tab back to it.

## Credits
This Software is used to interact with the Licensing System for Licensing Spigot Java Plugins by Leo Garbe (Leoko)

 - [Leoko's Website](https://skamps.eu)
 - [Leoko's AdvancedLicense System on Spigot](https://www.spigotmc.org/resources/advancedlicense.20823/) 

Coded through using the IDE [IntelliJ](https://www.jetbrains.com/idea/)

Do you need help? Send me a DM through Discord @ `Hope#5050` or Ping me on the [Skamps Dev-Team Discord Server](https://discord.com/invite/ycDG6rS).


