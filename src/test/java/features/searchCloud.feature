Feature: Verify cloud providers on ZDNet

  @regression
  Scenario: Validate cloud provider listing from zdnet
    Given the user opens the ZDNet cloud article
    Then the user should see "Amazon Web Services" in the article
    And the user should see "Microsoft Azure" in the article
    And the user should see "Google Cloud Platform" in the article
    When the user clicks the "View at AWS" button
    Then a new tab should open with URL "https://aws.amazon.com/what-is-aws/"
    And the page should contain the text "Cloud computing with AWS"