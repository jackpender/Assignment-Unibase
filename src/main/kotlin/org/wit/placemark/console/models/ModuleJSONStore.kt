package org.wit.placemark.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.placemark.console.helpers.*
import org.wit.placemark.console.views.aCollege
import java.util.*

private val logger = KotlinLogging.logger {}

val MODULE_JSON_FILE = "modules.json"
val gsonBuilderModule = GsonBuilder().setPrettyPrinting().create()
val listTypeModule = object : TypeToken<java.util.ArrayList<ModuleModel>>() {}.type


class ModuleJSONStore : ModuleStore {

    var modules = mutableListOf<ModuleModel>()
    var courses = mutableListOf<CourseModel>()

    var course = CourseModel()

    init {
        if (exists(MODULE_JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<ModuleModel> {
        return modules
    }

    override fun findOne(id: Long) : ModuleModel? {
        var foundModule: ModuleModel? = modules.find { p -> p.id == id }
        return foundModule
    }

    override fun create(module: ModuleModel) {
        module.id = generateRandomId()
        modules.add(module)
        serialize()
    }

    override fun update(module: ModuleModel) {
        var foundModule = findOne(module.id!!)
        if (foundModule != null) {
            foundModule.name = foundModule.name
            foundModule.description = foundModule.description
            foundModule.credits = foundModule.credits
        }
        serialize()
    }

    override fun delete(module: ModuleModel) {
        var foundModule = findOne(module.id!!)
        if(foundModule != null){
            modules.remove(foundModule)
        }
        serialize()
    }

    internal fun logAll() {
        modules.forEach { logger.info("${it}") }
    }

    fun serialize() {
        val jsonString = gsonBuilderModule.toJson(modules, listTypeModule)
        write(MODULE_JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(MODULE_JSON_FILE)
        modules = Gson().fromJson(jsonString, listTypeModule)
    }
}