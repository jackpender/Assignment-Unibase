package org.wit.placemark.console.controllers

import mu.KotlinLogging
import org.wit.placemark.console.models.ModuleJSONStore
import org.wit.placemark.console.models.ModuleMemStore
import org.wit.placemark.console.models.ModuleModel
import org.wit.placemark.console.views.ModuleView

class ModuleController {

    val modules = ModuleJSONStore()
    val moduleView = ModuleView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Unibase Console App" }
        println("Unibase Kotlin App Version 1.0")
    }

    fun add(){
        var aModule = ModuleModel()

        if (moduleView.addModuleData(aModule))
            modules.create(aModule)
        else
            logger.info("College Not Added")
    }

    fun list() {
        moduleView.listModules(modules)
    }

    fun update() {

        moduleView.listModules(modules)
        var searchId = moduleView.getId()
        val aModule = search(searchId)

        if(aModule != null) {
            if(moduleView.updateModuleData(aModule)) {
                modules.update(aModule)
                moduleView.showModule(aModule)
                logger.info("Module Updated : [ $aModule ]")
            }
            else
                logger.info("College Not Updated")
        }
        else
            println("College Not Updated...")
    }

    fun search() {
        val aModule = search(moduleView.getId())!!
        moduleView.showModule(aModule)
    }


    fun search(id: Long) : ModuleModel? {
        var foundModule = modules.findOne(id)
        return foundModule
    }
}