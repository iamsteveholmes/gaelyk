import java.net.URL
import net.sf.json.groovy.JsonSlurper

def pipeURL = 'http://pipes.yahoo.com/pipes/pipe.run?_id=UtVVPkx83hGZ2wKUKX1_0w&_render=json'
def pipeResult = urlFetchService.fetch(new URL(pipeURL))
def json = new JsonSlurper().parseText(new String(pipeResult.content))

html.html {
 head {
   style("""
     .item {
       border-bottom: 1px solid #eee;
       padding: 5px;
     }
     .date {
       font-style: italic;
     }
     #activities {
       border-top: 1px solid #eee;
     }
   """)
 }
 body {
  div(id: 'activities') {
   json.value.items.each { item ->
    div(class: 'item') {
     a(href: item.link, item.title)
     br()
     span(class: 'date', item.pubDate)
    }
   }
  }
 }
}