package exercise

import kotlin.test.Test
import kia.jkid.deserialization.deserialize
import kia.jkid.serialization.serialize
import kotlin.test.Ignore
import kotlin.test.assertEquals

data class Book(val title: String, val price: Double)
data class BookStore(val bookPrice: Map<String, Double>)
data class Library(val sections: Map<String, Book>)

@Ignore
class MapTest {
    private val bookStore = BookStore(mapOf("Catch-22" to 10.92, "The Lord of the Rings" to 11.49))
    private val json = """{"bookPrice": {"Catch-22": 10.92, "The Lord of the Rings": 11.49}}"""
    private val library = Library(
        mapOf(
            "fiction" to Book("Catch-22", 10.92),
            "fantasy" to Book("The Lord of the Rings", 11.49)
        )
    )
    private val libraryJson = """{"sections": {"fiction": {"price": 10.92, "title": "Catch-22"}, "fantasy": {"price": 11.49, "title": "The Lord of the Rings"}}}"""

    @Test fun testSerialization() {
        println(serialize(bookStore))
        assertEquals(json, serialize(bookStore))
    }

    @Test fun testDeserialization() {
        assertEquals(bookStore, deserialize(json))
    }

    @Test fun testObjectMapSerialization() {
        println(serialize(library))
        assertEquals(libraryJson, serialize(library))
    }

    @Test fun testObjectMapDeserialization() {
        assertEquals(library, deserialize<Library>(libraryJson))
    }
}