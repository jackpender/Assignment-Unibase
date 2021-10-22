package org.wit.placemark.console.views

//import org.wit.placemark.console.main.CollegeView
import org.wit.placemark.console.main.college
import org.wit.placemark.console.main.colleges
//import org.wit.placemark.console.controllers.search
import org.wit.placemark.console.models.CollegeMemStore
import org.wit.placemark.console.models.CollegeModel

import org.wit.placemark.console.controllers.CollegeController
import org.wit.placemark.console.models.CourseMemStore
import org.wit.placemark.console.models.CourseModel

var controller = CollegeController()

class CollegeView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("Main Menu")
        println(" 1. Add a College/Course/Module: ")
//        println(" 2. Add a Course: ")
//        println(" 3. Add a Module: ")
        println(" 2. List College/Course/Module: ")
/*        println(" 5. List Courses: ")
        println(" 6. List Modules: ")*/
        println(" 3. Search College/Course/Module: ")
        println(" 4. Delete College/Course/Module: ")
        println(" 5. Update College/Course/Module")
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

    fun addMenu() : Int {

        var option : Int
        var input: String?

        println("Add Menu")
        println(" 1. Add University/College: ")
        println(" 2. Add a Course: ")
        println(" 3. Add a Module: ")
        println("-1. Exit")
        println()
        print("Enter an integer : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty()) {
            input.toInt()
        }
        else
            -9
        return option
    }

    fun listMenu() : Int {

        var option : Int
        var input: String?

        println("List Menu")
        println(" 1. List University/College: ")
        println(" 2. List Courses: ")
        println(" 3. List Modules: ")
        println("-1. Exit")
        println()
        print("Enter an integer : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty()) {
            input.toInt()
        }
        else
            -9
        return option
    }

    fun searchMenu() : Int {

        var option : Int
        var input: String?

        println("Search Menu")
        println(" 1. Search University/College: ")
        println(" 2. Search Courses: ")
        println(" 3. Search Modules: ")
        println("-1. Exit")
        println()
        print("Enter an integer : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty()) {
            input.toInt()
        }
        else
            -9
        return option
    }

    fun deleteMenu() : Int {

        var option : Int
        var input: String?

        println("Delete Menu")
        println(" 1. Delete University/College: ")
        println(" 2. Delete Courses: ")
        println(" 3. Delete Modules: ")
        println("-1. Exit")
        println()
        print("Enter an integer : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty()) {
            input.toInt()
        }
        else
            -9
        return option
    }

    fun updateMenu() : Int {

        var option : Int
        var input: String?

        println("Update Menu")
        println(" 1. Update University/College: ")
        println(" 2. Update a Course: ")
        println(" 3. Update a Module: ")
        println("-1. Exit")
        println()
        print("Enter an integer : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty()) {
            input.toInt()
        }
        else
            -9
        return option
    }

    fun listColleges(colleges : CollegeMemStore) {
        println("List All Colleges")
        println()
        for(i in colleges.colleges) {
            println(
                "College Name: " + i.name +
                "\nCollege Eircode: " + i.address +
                        "\nCollege Courses: " + i.courses +
                        "\nCollege ID: " + i.id)
            println()
        }
    }

    /*fun listCourses(courses: CourseMemStore) {
        println("List All Courses")
        println()
        for(i in college.courses) {
            println(
                "Course Name: " + i.name +
                        "\nCourse Description: " + i.description +
                        "\nCourse Years: " + i.years +
                        "\nCourse ID: " + i.id)
            println()
        }
    }*/

    fun showCollege(college : CollegeModel) {
        if(college != null) {
            println(" -- College Details -- ")
            println()
            print(
                "College Name: " + college.name +
                        "\nCollege Eircode: " + college.address + "\n"
            )
        }
        else
            println("College Not Found...")
    }

    /*fun showCourse(course : CourseModel) {
        if(course != null) {
            println(" -- Course Details -- ")
            print(
                "Course Name: " + course.name +
                        "\nCourse Description: " + course.description +
                        "\nCourse Number of Years: " + course.years + "\n"
            )
        }
        else
            println("Course Not Found...")
    }*/

    /*fun showModule(module : ModuleModel) {
        if(module != null) {
            println(" -- Module Details -- ")
            print(
                "Module Name: " + module.name +
                        "\nModule Description: " + module.description +
                        "\nCourse Number of Credits: " + module.credits + "\n"
            )
        }
        else
            println("Module Not Found...")
    }*/

    fun addCollegeData(college : CollegeModel) : Boolean {
        println(" -- Add College -- ")
        println()
        print("Enter College Name: ")
        college.name = readLine()!!
        print("Enter College Address: ")
        college.address = readLine()!!

        return college.name.isNotEmpty() && college.address.isNotEmpty()
    }

    fun deleteCollegeData(college: CollegeModel) : Boolean {
        println("Delete College")
        println()
        if (college != null) {
            return true
        }
        return false
    }

    fun updateCollegeData(college : CollegeModel) : Boolean {

        var tempName: String?
        var tempAddress: String?

        if (college != null) {
            print("Enter a new Name for [ " + college.name + " ] : ")
            tempName = readLine()!!
            print("Enter a new Eircode for [ " + college.address + " ] : ")
            tempAddress = readLine()!!

            if (!tempName.isNullOrEmpty() && !tempAddress.isNullOrEmpty() && tempAddress.length == 7) {
                college.name = tempName
                college.address = tempAddress
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}