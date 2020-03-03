package com.nurbot

import org.jf.dexlib2.DexFileFactory
import org.jf.dexlib2.iface.DexFile

object Main {

  @kotlin.jvm.JvmStatic
  fun main(args: Array<String>) {
    println("Start analyzing classes.dex")
    val dexFile: DexFile = DexFileFactory.loadDexFile("classes.dex", 19 /*api level*/)
    println("Read configuration properties from src/main/resources/config.properties")
    val propertyReader = PropertyReader()
    val configuration: java.util.Properties = propertyReader.dex2ApiProperties!!
    val packageName: String = configuration.getProperty("package")
    val packageNameIgnore: String = configuration.getProperty("packageIgnore")
    val methodIgnores: List<String> =
      listOf(*configuration.getProperty("methodIgnore").split("#").toTypedArray())
    println("Package to investigate:$packageName")
    println("Additional package to ignore:$packageNameIgnore")
    println("ignoring " + methodIgnores.size + " methods.")
    for (classDef in dexFile.classes) {
      if (classDef.type.startsWith("L$packageName") && !classDef.type.startsWith("L$packageNameIgnore")) {
        println(classDef.type)
        for (method in classDef.methods) {
          if (!methodIgnores.contains(method.name)) {
            println("-" + method.name + " " + method.returnType)
            for (parameter in method.parameters) {
              println("---" + parameter.type + parameter.name)
            }
          }
        }
      }
    }
  }
}