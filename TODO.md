#TODO

##2013-04-08
- Rename repo to bukkit-sgcore

##2013-04-06
- /drop command to drop things at locations, rather than /give, for more immersive spawning
- easy /{plugname} reload support from SGPlugin 

##2013-04-04
- DONE: API to drop ItemStacks in the world
- DONE: Move GeoIP API from GeoSpawn to SGCore
  - DONE: Softdepend GeoIPTools
  - API only instantiates if GeoIPTools is present (should throw a MissingDependencyException)
  - Use this? http://stackoverflow.com/questions/7139743/conditional-class-import-load
- SoundChangeApplier
 - http://www.zompist.com/sca2.html  
 - http://metoojava.wordpress.com/2010/06/20/execute-javascript-from-java/
 - http://dev.bukkit.org/server-mods/scriptcraft/