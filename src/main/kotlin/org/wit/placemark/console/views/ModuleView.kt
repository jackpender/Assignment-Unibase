package org.wit.placemark.console.views

import org.wit.placemark.console.main.module
import org.wit.placemark.console.main.modules

import org.wit.placemark.console.controllers.ModuleController
import org.wit.placemark.console.models.*

var moduleController = ModuleController()

class ModuleView {

    fun listModules(modules : ModuleJSONStore) {
        println("List All Modules")
        println()
        for(i in modules.modules) {
            println(
                "Module Name: " + i.name +
                        "\nModule Description: " + i.description +
                        "\nModule Credits: " + i.credits +
                        "\nModule ID: " + i.id)
            println()
        }
    }

    fun showModule(module : ModuleModel) {
        if(module != null) {
            println(" -- Module Details -- ")
            println()
            print(
                "Module Name: " + module.name +
                        "\nModule Description: " + module.description +
                        "\nCourse Number of Credits: " + module.credits + "\n"
            )
        }
        else
            println("Module Not Found...")
    }

    fun addModuleData(module : ModuleModel) : Boolean {
        println(" -- Add Module -- ")
        println()
        print("Enter Module Name: ")
        module.name = readLine()!!
        print("Enter Module Description: ")
        module.description = readLine()!!
        print("Enter Module Number of Credits: ")
        module.credits = readLine()!!.toInt()

        return module.name.isNotEmpty() && module.description.isNotEmpty() && module.credits != null
    }

    fun deleteModuleData(module: ModuleModel) : Boolean {
        println(" -- Delete Module -- ")
        println()
        if (module != null) {
            return true
        }
        return false
    }

    fun updateModuleData(module : ModuleModel) : Boolean {

        var tempName: String?
        var tempDescription: String?
        var tempCredits: Int?

        if (module != null) {
            print("Enter a new Name for [ " + module.name + " ] : ")
            tempName = readLine()!!
            print("Enter a new Description for [ " + module.description + " ] : ")
            tempDescription = readLine()!!
            print("Enter a new Module Number of Credits for [ " + module.credits + " ] : ")
            tempCredits = readLine()!!.toInt()

            if (!tempName.isNullOrEmpty() && !tempDescription.isNullOrEmpty() && tempCredits != null) {
                module.name = tempName
                module.description = tempDescription
                module.credits = tempCredits
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String?
        var searchId : Long
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}