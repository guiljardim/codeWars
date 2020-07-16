package com.example.codewars.util

import android.view.View
import android.widget.TextView
import com.example.codewars.data.model.Overall
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.Assert.assertEquals
import org.junit.Test

class ExtensionTest {

    @Test
    fun `test format to exhibition whit white space`(){
        val formatTest = "Teste".formatToExhibition("teste", true)
        assertEquals("teste Teste", formatTest)
    }

    @Test
    fun `test format to exhibition without white space`(){
        val formatTest = "Teste".formatToExhibition("teste", false)
        assertEquals("testeTeste", formatTest)
    }

    @Test
    fun `test get better language`() {
        val overallRankTwo = Overall(2, "Test", "Blue",1234)
        val overallRankOne = Overall(1, "Test", "Blue",1234)
        val map = mapOf("Javascript" to overallRankOne, "Python" to overallRankTwo)

        assertEquals("Javascript", map.getBetterLanguage())
    }

}