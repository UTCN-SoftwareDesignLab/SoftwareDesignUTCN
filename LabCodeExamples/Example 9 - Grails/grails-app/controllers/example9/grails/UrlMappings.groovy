package example9.grails

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        get "/items/$n/$desc"(controller: "item", action: 'byNameAndDescription')

        get "/items/byName/$name"(controller: "item", action: 'byName')


        "/"(view: "/index")
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
