package org.wit.placemark.console.controllers

import mu.KotlinLogging
import org.wit.placemark.console.main.course
import org.wit.placemark.console.main.courses
import org.wit.placemark.console.main.modules
import org.wit.placemark.console.models.CollegeMemStore
import org.wit.placemark.console.models.CollegeModel
import org.wit.placemark.console.models.CourseModel
import org.wit.placemark.console.models.ModuleModel
import org.wit.placemark.console.views.CollegeView
import org.wit.placemark.console.views.courseController
import org.wit.placemark.console.views.CourseView
import org.wit.placemark.console.views.moduleController

var courseView = CourseView()

class CollegeController {

    val colleges = CollegeMemStore()
    val collegeView = CollegeView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Unibase Console App" }
        println("Unibase Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> addMenu()
//                2 -> addCourse()
//                3 -> addModule()
                2 -> listMenu()
//                5 -> listCourses()
//                6 -> moduleController.list()
                3 -> searchMenu()
                4 -> deleteMenu()
                5 -> updateMenu()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Unibase Console App" }
    }

    fun menu() :Int { return collegeView.menu() }

    fun addMenu() {
        var input: Int

        input = menuForAdd()
        when(input) {
            1 -> add()
            2 -> addCourse()
            3 -> addModule()
            -1 -> println("Exiting Menu")
        }
        println()
    }

    fun listMenu() {
        var input: Int

        input = menuForList()
        when(input) {
            1 -> list()
            2 -> listCourses()
            3 -> listModules()
            -1 -> println("Exiting Menu")
        }
        println()
    }

    fun searchMenu() {
        var input: Int

        input = menuForSearch()
        when(input) {
            1 -> search()
            2 -> searchCourse()
            3 -> searchModule()
            -1 -> println("Exiting Menu")
        }
        println()
    }

    fun deleteMenu() {
        var input: Int

        input = menuForDelete()
        when(input) {
            1 -> delete()
            2 -> deleteCourse()
            3 -> deleteModule()
            -1 -> println("Exiting Menu")
        }
        println()
    }

    fun updateMenu() {
        var input: Int

        input = menuForUpdate()
        when(input) {
            1 -> update()
            2 -> updateCourse()
            3 -> updateModule()
            -1 -> println("Exiting Menu")
        }
        println()
    }

    fun menuForAdd(): Int {
        return collegeView.addMenu()
    }

    fun menuForList(): Int {
        return collegeView.listMenu()
    }

    fun menuForSearch(): Int {
        return collegeView.searchMenu()
    }

    fun menuForDelete(): Int {
        return collegeView.deleteMenu()
    }

    fun menuForUpdate(): Int {
        return collegeView.updateMenu()
    }

    fun add(){
        var aCollege = CollegeModel()

        if (collegeView.addCollegeData(aCollege))
            colleges.create(aCollege)
        else
            logger.info("College Not Added")
    }

    fun addCourse() {
        collegeView.listColleges(colleges)
        var searchId = collegeView.getId()
        val aCollege = search(searchId)

        var aCourse = CourseModel()

        if (aCollege != null) {
            if (aCourse != null) {
                if (courseView.addCourseData(aCourse)) {
                    courses.create(aCourse)
                    aCollege.courses.add(aCourse)
                } else {
                    logger.info("Course Not Added")
                }
            } else {
                logger.info("Course Not Added")
            }
        }
        else{
            logger.info("College Not Found")
        }

    }

    fun addModule() {
        collegeView.listColleges(colleges)
        var collegeId = collegeView.getId()
        var aCollege = search(collegeId)

        if (aCollege != null) {
            courseView.listCollegeCourses(aCollege.courses)
            var searchId = courseView.getId()
            val aCourse = searchCourse(searchId)

            var aModule = ModuleModel()

            if (aCourse != null) {
                if (aModule != null) {
                    if (moduleView.addModuleData(aModule)) {
                        modules.create(aModule)
                        if (aCourse != null) {
                            aCourse.modules.add(aModule)
                        }
                    } else {
                        logger.info("Module Not Added")
                    }
                } else {
                    logger.info("Module Not Added")
                }
            } else {
                logger.info("Course Not Found")
            }
        }
        else{
            logger.info("College Not Found")
        }
    }

    fun list() {
        collegeView.listColleges(colleges)
    }

    fun listCourses() {
        collegeView.listColleges(colleges)
        var searchId = collegeView.getId()
        val aCollege = search(searchId)

        if (aCollege != null) {
            print(" -- "+aCollege.name+" -- \n")
            for(i in aCollege.courses){
                println(
                    "Course Name: " + i.name +
                            "\nCourse Description: " + i.description +
                            "\nCourse Years: " + i.years +
                            "\nCourse ID: " + i.id + "\n")
            }
        }
        else{
            logger.info("Course Does Not Exist")
        }
    }

    fun listModules() {
        collegeView.listColleges(colleges)
        var searchId = collegeView.getId()
        val aCollege = search(searchId)

        courseView.listCourses(courses)
        var courseId = courseView.getId()
        val aCourse = searchCourse(courseId)

        if (aCollege != null) {
            print(" -- "+aCollege.name+" -- \n")
        }
        if (aCourse != null) {
            print(" -- "+aCourse.name+" -- \n")
            for(i in aCourse.modules){
                println(
                    "Module Name: " + i.name +
                            "\nModule Description: " + i.description +
                            "\nModule Credits: " + i.credits +
                            "\nModule ID: " + i.id +"\n")
            }
        }
        else{
            logger.info("Module Does Not Exist")
        }
    }

    fun delete(){
        collegeView.listColleges(colleges)
        var searchId = collegeView.getId()
        val aCollege = search(searchId)
        if(aCollege != null) {
            if(collegeView.deleteCollegeData(aCollege)) {
                colleges.delete(aCollege)
                logger.info("College Removed : [ $aCollege ]")
            }
            else
                logger.info("College Not Removed")
        }
        else
            println("College Not Removed...")
    }

    fun deleteCourse(){
        collegeView.listColleges(colleges)
        var searchId = collegeView.getId()
        val aCollege = search(searchId)
        if(aCollege != null){
            courseView.listCourses(courses)
            var courseId = courseView.getId()
            val aCourse = searchCourse(courseId)
            if(aCourse != null){
                if(courseView.deleteCourseData(aCourse)){
                    courses.delete(aCourse)
                    logger.info("Course Removed : [ $aCourse ]")
                }
            }
            else{
                logger.info("Course Not Found")
            }
        }
        else{
            logger.info("College Not Found")
        }
    }

    fun deleteModule(){
        collegeView.listColleges(colleges)
        var searchId = collegeView.getId()
        val aCollege = search(searchId)
        if(aCollege != null){
            courseView.listCourses(courses)
            var courseId = courseView.getId()
            val aCourse = searchCourse(courseId)
            if(aCourse != null){
                moduleView.listModules(modules)
                var moduleId = moduleView.getId()
                val aModule= searchModule(moduleId)
                if(aModule != null){
                    if(moduleView.deleteModuleData(aModule)){
                        modules.delete(aModule)
                        logger.info("Module Removed : [ $aModule ]")
                    }
                }
                else{
                    logger.info("Module Not Found")
                }
            }
            else{
                logger.info("Course Not Found")
            }
        }
        else{
            logger.info("College Not Found")
        }
    }

    fun update() {
        collegeView.listColleges(colleges)
        var searchId = collegeView.getId()
        val aCollege = search(searchId)

        if(aCollege != null) {
            if(collegeView.updateCollegeData(aCollege)) {
                colleges.update(aCollege)
                collegeView.showCollege(aCollege)
                logger.info("College Updated : [ $aCollege ]")
            }
            else
                logger.info("College Not Updated")
        }
        else
            println("College Not Updated...")
    }

    fun updateCourse() {
        collegeView.listColleges(colleges)
        var searchId = collegeView.getId()
        val aCollege = search(searchId)

        if(aCollege != null) {
            courseView.listCourses(courses)
            var courseId = courseView.getId()
            val aCourse = searchCourse(courseId)
            if(aCourse != null) {
                if(courseView.updateCourseData(aCourse)) {
                    courses.update(aCourse)
                    courseView.showCourse(aCourse)
                    logger.info("Course Updated : [ $aCourse ]")
                }
                else{
                    logger.info("Course Not Updates")
                }
            }
            else{
                logger.info("Course Not Found")
            }
        }
        else {
            println("College Not Found")
        }
    }

    fun updateModule() {
        collegeView.listColleges(colleges)
        var searchId = collegeView.getId()
        val aCollege = search(searchId)

        if(aCollege != null) {
            courseView.listCourses(courses)
            var courseId = courseView.getId()
            val aCourse = searchCourse(courseId)
            if(aCourse != null){
                moduleView.listModules(modules)
                var moduleId = moduleView.getId()
                val aModule= searchModule(moduleId)
                if(aModule != null){
                    if(moduleView.updateModuleData(aModule)){
                        modules.update(aModule)
                        moduleView.showModule(aModule)
                        logger.info("Module Updated : [ $aModule ]")
                    }
                }
                else{
                    logger.info("Module Not Found")
                }
            }
            else{
                logger.info("Course Not Found")
            }
        }
        else {
            println("College Not Found")
        }
    }

    fun search() {
        val aCollege = search(collegeView.getId())!!
        if(aCollege != null) {
            collegeView.showCollege(aCollege)
        }
        else{
            logger.info("College Not Found")
        }
    }

    fun searchCourse() {
        val aCourse = searchCourse(courseView.getId())!!
        courseView.showCourse(aCourse)
    }

    fun searchModule() {
        val aModule = searchModule(moduleView.getId())!!
        moduleView.showModule(aModule)
    }

    fun search(id: Long) : CollegeModel? {
        var foundCollege = colleges.findOne(id)
        return foundCollege
    }

    fun searchCourse(id: Long) : CourseModel? {
        var foundCourse = courses.findOne(id)
        return foundCourse
    }

    fun searchModule(id: Long) : ModuleModel? {
        var foundModule = modules.findOne(id)
        return foundModule
    }
}