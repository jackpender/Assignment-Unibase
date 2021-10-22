package org.wit.placemark.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastid= 0L

internal fun getModuleId(): Long {
    return lastid++
}

class ModuleMemStore : ModuleStore {

    val modules = ArrayList<ModuleModel>()

    override fun findAll(): List<ModuleModel> {
        return modules
    }

    override fun findOne(id: Long) : ModuleModel? {
        var foundModule: ModuleModel? = modules.find { p -> p.id == id }
        return foundModule
    }

    override fun create(module: ModuleModel) {
        module.id = getModuleId()
        modules.add(module)
        logAll()
    }

    override fun update(module: ModuleModel) {
        var foundModule = findOne(module.id!!)
        if (foundModule != null) {
            foundModule.name = module.name
            foundModule.description = module.description
            foundModule.credits = module.credits
        }
    }

    override fun delete(module: ModuleModel) {
        var foundModule = findOne(module.id!!)
        if(foundModule != null){
            modules.remove(module)
        }
    }

    internal fun logAll() {
        modules.forEach { logger.info("${it}") }
    }
}