import net.minecraftforge.gradle.user.UserBaseExtension
import java.util.*

buildscript {
    repositories {
        mavenCentral()
        maven {
            name = "Jitpack"
            setUrl("https://jitpack.io")
        }
        maven {
            name = "Forge"
            setUrl("https://maven.minecraftforge.net")
        }
    }
    dependencies {
        classpath("com.github.GregTechCE:ForgeGradle:FG_2.3-SNAPSHOT")
    }
}

apply {
    plugin("net.minecraftforge.gradle.forge")
}

val Project.minecraft: UserBaseExtension
    get() = extensions.getByName<UserBaseExtension>("minecraft")

val config: Properties = file("build.properties").inputStream().let {
    val prop = Properties()
    prop.load(it)
    return@let prop
}

val modVersion = config["gregicality.version"] as String
val mcVersion = config["mc.version"] as String
val forgeVersion = "$mcVersion-${config["forge.version"]}"
val shortVersion = mcVersion.substring(0, mcVersion.lastIndexOf("."))
val strippedVersion = shortVersion.replace(".", "") + "0";

version = "$mcVersion-$modVersion"
group = "gregicadditions"

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

configure<BasePluginConvention> {
    archivesBaseName = "Gregicality"
}

configure<UserBaseExtension> {
    version = forgeVersion
    mappings = config["mcp.version"] as String
    runDir = "run"
    makeObfSourceJar = false;
    replace("@VERSION@", modVersion)
    replaceIn("Gregicality.java")
}

repositories {
    maven {
        name = "JEI"
        setUrl("http://dvs1.progwml6.com/files/maven/")
    }
    maven {
        name = "CraftTweaker"
        setUrl("https://maven.blamejared.com/")
    }
    maven {
        name = "Forestry"
        setUrl("http://maven.ic2.player.to")
    }
    maven {
        name = "CurseForge"
        setUrl("https://minecraft.curseforge.com/api/maven")
    }
    maven {
        name = "CCL, CASM"
        setUrl("http://chickenbones.net/maven/")
    }
    maven {
        name = "CTM"
        setUrl("https://maven.tterrag.com/")
    }
    maven {
        name = "OpenComputers"
        setUrl("https://maven.cil.li/")
    }
    maven {
        name = "McJtyLib, XNet"
        setUrl("http://maven.k-4u.nl")
    }
}

dependencies {

    // These 7 will always be in game
    "deobfCompile"("gregtechce:gregtech:$mcVersion:${config["gregtech.version"]}")
    "deobfCompile"("codechicken-lib-1-8:CodeChickenLib-$mcVersion:${config["ccl.version"]}:universal")
    "deobfCompile"("codechicken:ChickenASM:$shortVersion-${config["chickenasm.version"]}")
    "deobfCompile"("mezz.jei:jei_$mcVersion:${config["jei.version"]}")
    "deobfCompile"("mcjty.theoneprobe:TheOneProbe-$shortVersion:$shortVersion-${config["top.version"]}")
    "deobfCompile"("CraftTweaker2:CraftTweaker2-MC$strippedVersion-Main:${config["crafttweaker.version"]}")
    "deobfCompile"("team.chisel.ctm:CTM:MC$mcVersion-${config["ctm.version"]}")

    // Change to "deobfCompile" to add one of these to game
    "deobfProvided"("net.sengir.forestry:forestry_$mcVersion:${config["forestry.version"]}")
    "provided"("slimeknights.mantle:Mantle:$shortVersion-${config["mantle.version"]}")
    "provided"("slimeknights:TConstruct:$mcVersion-${config["ticon.version"]}")
    "provided"("com.github.mcjty:xnet:$shortVersion-${config["xnet.version"]}")
    "provided"("com.github.mcjty:mcjtylib:$shortVersion-${config["mcjtylib.version"]}")
    "provided"("li.cil.oc:OpenComputers:MC$mcVersion-${config["oc.version"]}")
    "provided"("binnie:binnie-mods-$mcVersion:${config["binnie.version"]}")
    "provided"("exnihilocreatio:exnihilocreatio:$mcVersion-${config["exnihilo.version"]}") {
        isTransitive = false
    }

    // Change to "compile" to add one of these to game
    "provided"(files("extdeps/appliedenergistics2-rv6-stable-7.jar"))
    "compileOnly"(files("extdeps/refinedstorage-1.6.15.jar"))
    "compileOnly"(files("extdeps/Cucumber-1.12.2-1.1.3.jar"))
    "compileOnly"(files("extdeps/MysticalAgriculture-1.12.2-1.7.5.jar"))
    "compileOnly"(files("extdeps/MysticalAgradditions-1.12.2-1.3.2.jar"))

    // JUnit testing used for GitHub Actions
    "testImplementation"("junit:junit:${config["junit.version"]}")
}

val processResources: ProcessResources by tasks
val sourceSets: SourceSetContainer = the<JavaPluginConvention>().sourceSets

processResources.apply {
    inputs.property("version", modVersion)
    inputs.property("mcversion", forgeVersion)

    from(sourceSets["main"].resources.srcDirs) {
        include("mcmod.info")
        expand(mapOf("version" to modVersion, "mcversion" to forgeVersion))
    }

    from(sourceSets["main"].resources.srcDirs) {
        exclude("mcmod.info")
    }

    // Access Transformer jar manifest info
    rename("(.+_at.cfg)", "META-INF/$1")
}

// Jar manifest for CoreMod
val jar: Jar by tasks
jar.apply {
    manifest {
        attributes(mapOf("FMLAT" to "gregicality_at.cfg",
            "FMLCorePluginContainsFMLMod" to "true",
            "FMLCorePlugin" to "gregicadditions.coremod.GACoreMod"))
    }
}
