package com.project.stackoverflowjetpackcompose.apiService

import com.project.stackoverflowjetpackcompose.model.QuestionId.LastEditor
import com.project.stackoverflowjetpackcompose.model.QuestionId.QuestionItem
import com.project.stackoverflowjetpackcompose.model.Questions.BadgeCounts
import com.project.stackoverflowjetpackcompose.model.Questions.Owner
import com.project.stackoverflowjetpackcompose.model.Questions.Item
import com.project.stackoverflowjetpackcompose.model.Questions.Questions
import com.project.stackoverflowjetpackcompose.model.Tags.Tag

object FakeItem{

    val item1 = Item(5, "abc","abc",1345677,"answered",987654,true,
        12345,1456678,"www.ffhjfuhefhuh.com", Owner(4,1, BadgeCounts(5,1,2),
            "Robert","www.robert.com","https://i.stack.imgur.com/jk23XN.jpg",
            1,142635767,"registered"),2463563,0,listOf("android","mvvm","pagination"),"what is a dp in Android?",
        33)
    val item2 = Item(4, "abcddgwjfhj","abcfgfufuh",1346789,"answered",678904,true,
        189076,67896543,"www.dgduduyqyduu.com", Owner(2,2, BadgeCounts(9,0,2),
            "Anna","www.anna-m.com","https://i.stack.imgur.com/dgdygudhu.pg",
            1,142677,"registered"),2498765,0,listOf("php","drupal","drupal-module"),"what is a php?",
        2)

    val item3 = Item(4, "abcddgdgduhj","abcfdudhuhdhhuh",1348979,"answered",6782344,true,
        234076,9989097,"www.sskutuu.com", Owner(5,5, BadgeCounts(2,0,1),
            "William","www.william-p.com","https://i.stack.imgur.com/dgdgydgtu.pg",
            1,1342677,"unregistered"),2345665,0,listOf("array","algorithm","optimization"),"Binary Search Optimisation",
        2)
    val questionitem = Questions(true, listOf(item1, item2, item3) ,4567,178)


    val questionItem1 = com.project.stackoverflowjetpackcompose.model.QuestionId.Item(5,"abc","abc",12345,true,1234567,156789,
    LastEditor(4,com.project.stackoverflowjetpackcompose.model.QuestionId.BadgeCounts(5,1,2),"Robert",
    "www.robert.com","https://i.stack.imgur.com/jk23XN.jpg",1,142635767,"registered"),"www.ffhjfuhefhuh.com",
            com.project.stackoverflowjetpackcompose.model.QuestionId.Owner(4,com.project.stackoverflowjetpackcompose.model.QuestionId.BadgeCounts(5,1,2),"Doe"
,"www.doe.com","https://i.stack.imgur.com/jk23XN.jpg",1,142635767,"registered"),245678,0,"",listOf("android","mvvm","pagination"),"what is a dp in Android?",33)

    val questionItem2 = com.project.stackoverflowjetpackcompose.model.QuestionId.Item(6,"abcdefg","abcdjhj",1246745,true,1236767,15346789,
        LastEditor(4,com.project.stackoverflowjetpackcompose.model.QuestionId.BadgeCounts(5,4,2),"William",
            "www.rwilliamt.com","https://i.stack.imgur.com/jk23456.jpg",1,1635767,"unregistered"),"www.fefhuh.com",
        com.project.stackoverflowjetpackcompose.model.QuestionId.Owner(6,com.project.stackoverflowjetpackcompose.model.QuestionId.BadgeCounts(5,6,2),"Joe"
            ,"www.joe.com","https://i.stack.imgur.com/jk23XN.jpg",1,1425767,"registered"),245678678,0,"",listOf("android","mvvm","pagination"),"what is a dp in Android?",33)

    val questionItems = QuestionItem(true, listOf(questionItem1,questionItem2),100,10)

    val tagItem1 = com.project.stackoverflowjetpackcompose.model.Tags.Item(5, true,true,true, "android")
    val tagItem2 = com.project.stackoverflowjetpackcompose.model.Tags.Item(5, true,true,true, "kotlin")

    val tagItems = Tag(true, listOf(tagItem1,tagItem2),100,10)

}
