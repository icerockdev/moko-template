package org.example.library.feature.list

val newsResponseMock = """
{
  "status": "ok",
  "totalResults": 2,
  "articles": [
    {
      "source": {
        "id": null,
        "name": "New York Post"
      },
      "author": "Juliegrace Brufke",
      "title": "Trump takes jab at 'lunacy' of Johnson & Johnson vaccination pause - New York Post ",
      "description": "Former President Donald Trump used a New York Post opinion piece to take aim at the Biden administration on Friday evening for allowing for a pause in the Johnson & Johnson coronavirus vaccine,…",
      "url": "https://nypost.com/2021/04/16/trump-takes-jab-at-bidens-lunacy-of-jj-vaccination-pause/",
      "urlToImage": "https://nypost.com/wp-content/uploads/sites/2/2021/04/trump-calls-out-biden-jandj-pause-index.jpg?quality=90&strip=all&w=1200",
      "publishedAt": "2021-04-17T00:41:00Z",
      "content": "Former President Donald Trump used a New York Post opinion piece to take aim at the Biden administration on Friday evening for allowing for a pause in the Johnson &amp; Johnson coronavirus vaccine, a… [+2798 chars]"
    },
    {
      "source": {
        "id": null,
        "name": "CNBC"
      },
      "author": "Tyler Clifford",
      "title": "Cramer's lightning round: I'd buy Lithia Motors right here - CNBC",
      "description": "\"Mad Money\" host Jim Cramer rings the lightning round bell, which means he's giving his answers to callers' stock questions at rapid speed.",
      "url": "https://www.cnbc.com/2021/04/16/cramer-lightning-round-i-would-buy-lithia-motors-right-here.html",
      "urlToImage": "https://image.cnbcfm.com/api/v1/image/103507383-Lightning-Round.jpg?v=1459356509",
      "publishedAt": "2021-04-17T00:06:00Z",
      "content": "Lithia Motors: \"I like Lithia. ... I'd buy it right here.\"\r\nWorld Wrestling Entertainment: \"I think you're in good hands.\"\r\nInnovative Industrial Properties: \"It's one of the safest ways to invest in… [+528 chars]"
    }
  ]
}
""".trimIndent()
