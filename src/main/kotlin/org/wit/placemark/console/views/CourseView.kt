package org.wit.placemark.console.views

//import org.wit.placemark.console.main.CollegeView
//import org.wit.placemark.console.main.search
import org.wit.placemark.console.models.CourseMemStore
import org.wit.placemark.console.models.CourseModel

import org.wit.placemark.console.controllers.CourseController
import org.wit.placemark.console.controllers.collegeController
import org.wit.placemark.console.controllers.courseView
import org.wit.placemark.console.main.*
import org.wit.placemark.console.models.CollegeModel

var courseController  = CourseController()
var aCollege = CollegeModel()

class CourseView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("Main Menu")
        println(" 1. Add University/College: ")
        println(" 2. Add a Course: ")
        println(" 3. Add a Module: ")
        println(" 4. List Colleges: ")
        println(" 5. List Courses: ")
        println(" 6. List Modules: ")
        println(" 7. Search College: ")
        println(" 8. Delete College: ")
        println(" 9. Update College")
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

    fun listCourses(courses: CourseMemStore) {
        println("List All Courses")
        println()
        for(i in courses.courses) {
            println(
                "Course Name: " + i.name +
                        "\nCourse Description: " + i.description +
                        "\nCourse Years: " + i.years +
                        "\nCourse ID: " + i.id)
            println()
        }
    }

    fun listCollegeCourses(courses: ArrayList<CourseModel>) {
        println("List All Courses")
        println()
        for(i in courses) {
            println(
                "Course Name: " + i.name +
                        "\nCourse Description: " + i.description +
                        "\nCourse Years: " + i.years +
                        "\nCourse ID: " + i.id)
            println()
        }
    }

    fun showCourse(course : CourseModel) {
        if(course != null) {
            println(" -- Course Details -- ")
            println()
            print(
                "Course Name: " + course.name +
                        "\nCourse Description: " + course.description +
                        "\nCourse Number of Years: " + course.years + "\n"
            )
        }
        else
            println("Course Not Found...")
    }

    fun addCourseData(course : CourseModel) : Boolean {
        print(" -- Add Course -- ")
        println()
        print("Enter Course Name: ")
        course.name = readLine()!!
        print("Enter Course Desciption: ")
        course.description = readLine()!!
        print("Enter Course Number of Years: ")
        course.years = readLine()!!.toInt()

        return course.name.isNotEmpty() && course.description.isNotEmpty() && course.years != null
    }

    /*fun deleteCourseData(course: CourseModel): Boolean{
        println("Delete Course")
        println()
        listCourses(courses)
        var searchId = getId()
        val thisCourse = courseController.search(searchId)
        print("this "+thisCourse)

        if(thisCourse != null && thisCourse.id == searchId) {
            courses.courses.remove(thisCourse)
        }
        else {
            println("College Not Removed...")
        }
    }*/

    fun deleteCourseData(course: CourseModel) : Boolean {
        println(" -- Delete Course -- ")
        println()
        if (course != null) {
            return true
        }
        return false
    }

    fun updateCourseData(course: CourseModel) : Boolean {

        var tempName: String?
        var tempDescription: String?
        var tempYears: Int?

        if (course != null) {
            print("Enter a new Name for [ " + course.name + " ] : ")
            tempName = readLine()!!
            print("Enter a new Description for [ " + course.description + " ] : ")
            tempDescription = readLine()!!
            print("Enter a new Course Number of Years for [ " + course.years + " ] : ")
            tempYears = readLine()!!.toInt()

            if (!tempName.isNullOrEmpty() && !tempDescription.isNullOrEmpty() && tempYears != null) {
                course.name = tempName
                course.description = tempDescription
                course.years = tempYears
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