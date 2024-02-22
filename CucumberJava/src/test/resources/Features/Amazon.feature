Feature: Amazon Filter and Add to Cart Functionality


  Scenario: Filter and add a product to the cart on Amazon
    Given User is on Amazon.in homepage
    When User clicks on Fashion and selects Mens
    And User filters products by Average customer review of four stars and Up
    And user filters products by price range
    And User selects Allen Solley in Brands
    And User counts the number of results in the first page
    And User clicks on the second product and add it to Cart "Added to Cart"
    Then User validates that the number on the Cart is increased by "1"
    #Then User proceed to buy the item