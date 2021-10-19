package org.wit.placemark.console.main

import mu.KotlinLogging
import org.wit.placemark.console.models.CollegeModel
import org.wit.placemark.console.models.CourseModel
import org.wit.placemark.console.models.ModuleModel

private val logger = KotlinLogging.logger {}

var college = CollegeModel()
var course = CourseModel()
var module = ModuleModel()

val colleges = ArrayList<CollegeModel>()
val courses = ArrayList<CourseModel>()
val modules = ArrayList<ModuleModel>()


fun main(args: Array<String>){
    logger.info { "Launching UniBase Console App" }
    println("|| WELCOME TO UNIBASE ||")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addCollege()
            2 -> addCourse()
            3 -> addModule()
            4 -> listColleges()
            5 -> listCourses()
            6 -> listModules()
            7 -> searchCollege()
            8 -> deleteCollege()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down UniBase" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Add University/College: ")
    println(" 2. Add a Course: ")
    println(" 3. Add a Module: ")
    println(" 4. List Colleges: ")
    println(" 5. List Courses: ")
    println(" 6. List Modules: ")
    println(" 7. Search College: ")
    println(" 8. Delete College: ")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addCollege(){
    println(" -- Add College -- " +
            "")
    println()
    print("Enter College Name : ")
    college.name = readLine()!!
    print("Enter College Eircode: ")
    college.address = readLine()!!

    if (college.name.isNotEmpty() && college.address.length == 7) {
        college.id = colleges.size.toLong()
        colleges.add(college.copy())
        logger.info("College Added : [ $college ]")
    }
    else
        logger.info("College Not Added")
}

fun addCourse(){
    println(" -- Add Course -- " +
            "")
    println()
    print("Enter Course Name: ")
    course.name = readLine()!!
    print("Enter Course Description: ")
    course.description = readLine()!!
    print("Enter Course Number of Years: ")
    course.years = readLine()!!.toInt()

    if (course.name.isNotEmpty() && course.description.isNotEmpty() && course.years != null) {
        course.id = courses.size.toLong()
        courses.add(course.copy())
        logger.info("Course Added : [ $course ]")
    }
    else
        logger.info("Course Not Added")
}

fun addModule(){
    println(" -- Add Module -- " +
            "")
    println()
    print("Enter Module Name : ")
    module.name = readLine()!!
    print("Enter Module Description: ")
    module.description = readLine()!!
    print("Enter Modole Number of Credits: ")
    module.credits = readLine()!!.toInt()

    if (module.name.isNotEmpty() && module.description.isNotEmpty() && module.credits != null) {
        module.id = modules.size.toLong()
        modules.add(module.copy())
        logger.info("Module Added : [ $module ]")
    }
    else
        logger.info("Module Not Added")
}

fun listColleges() {
    println(" -- List All Colleges -- ")
    println()
    for(i in colleges) {
        println("College Name: " + i.name +
                "\nCollege Address: " + i.address +
                "\nCollege ID: " + i.id +
                "\nCourses: " + i.courses + "\n")
    }
}

fun listCourses() {
    println(" -- List All Courses -- ")
    println()
    for(i in courses){
        println("Course Name: " + i.name +
                "\nCourse Description: " + i.description +
                "\nCourse Years: " + i.years +
                "\nCourse ID: " + i.id +
                "\nModules: " + i.modules + "\n")
    }
}

fun listModules() {
    println(" -- List All Modules -- ")
    println()
    for(i in modules){
        println("Module Name: " + i.name +
                "\nModule Description: " + i.description +
                "\nModule Credits: " + i.credits +
                "\nModule ID: " + i.id + "\n")
    }
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

fun search(id: Long) : CollegeModel? {
    var foundCollege: CollegeModel? = colleges.find { p -> p.id == id }
    return foundCollege
}

fun searchCollege() {

    var searchId = getId()
    val thisCollege = search(searchId)

    if(thisCollege != null)
        println("College Name: ${thisCollege.name}\nCollege Eircode: ${thisCollege.address}\nCollege ID: ${thisCollege.id}\n")
    else
        println("College Not Found...")
}

fun deleteCollege() {
    println("Delete College")
    println()
    listColleges()
    var searchId = getId()
    val thisCollege = search(searchId)
    print("this "+thisCollege)

    if(thisCollege != null && thisCollege.id == searchId) {
        colleges.remove(colleges.get(searchId.toInt()))
    }
    else {
        println("College Not Removed...")
    }
}