package com.nurbot

import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*

class PropertyReader {
  private var properties: Properties? = null
  private var inputStream: InputStream? = null

  val dex2ApiProperties: Properties?
    get() {
      try {
        properties = Properties()
        val propFileName = "config.properties"
        inputStream = javaClass.classLoader.getResourceAsStream(propFileName)
        if (inputStream != null) {
          properties!!.load(inputStream)
        } else {
          throw FileNotFoundException("property file '$propFileName' not found in the classpath")
        }
      } catch (e: Exception) {
        println("Exception: $e")
      } finally {
        assert(inputStream != null)
        inputStream!!.close()
      }
      return properties
    }
}