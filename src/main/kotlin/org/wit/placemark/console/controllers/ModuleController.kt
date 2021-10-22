package org.wit.placemark.console.controllers

import mu.KotlinLogging
import org.wit.placemark.console.models.ModuleMemStore
import org.wit.placemark.console.models.ModuleModel
import org.wit.placemark.console.views.ModuleView

class ModuleController {

    val modules = ModuleMemStore()
    val moduleView = ModuleView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Unibase Console App" }
        println("Unibase Kotlin App Version 1.0")
    }

    /*fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Unibase Console App" }
    }*/

    /*fun menu() :Int { return moduleView.menu() }*/

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