describe("renders homepage", ()=>{
    it("renders", ()=>{
        cy.wait(2000)
        cy.visit("/")
        cy.wait(10000)
        cy.get("#jumbotron").should("exist");
    });

    function checkIfLogedIn() {
        cy.get('#loginForm').should("exist").then((body) => {
            if (body) {
                cy.get('#email').type('1@1.1')
                cy.wait(100)
                cy.get('#password').type('212121')
                cy.wait(1000)
                cy.get('#loginForm').click()
                cy.wait(2000)
                // checkIfLogedIn()
            }
        })
    }

    it('Login', ()=>{
        // cy.wait(1000)
        // cy.visit('/')
        // cy.wait(1000)
        // cy.contains(' Login').click()
        // cy.wait(1000)
        // checkIfLogedIn()

        // while(cy.get('#loginForm').should("exist")){
        //     cy.get('#email').type('1@1.1')
        //     cy.wait(100)
        //     cy.get('#password').type('212121')
        //     cy.wait(1000)
        //     cy.get('#loginForm').click()
        //     cy.wait(3000)
        // }
        
        // if(cy.get('#loginForm').should("exist")){
        //     cy.get('#email').type('1@1.1')
        //     cy.wait(100)
        //     cy.get('#password').type('212121')
        //     cy.wait(1000)
        //     cy.get('#loginForm').click()
        //     cy.wait(3000)
        // }

        // cy.get('#email').should('have.value', '').then(($login) => {
        //     if($login){
        //         cy.get('#email').type('1@1.1')
        //         cy.wait(100)
        //         cy.get('#password').type('212121')
        //         cy.wait(1000)
        //         cy.get('#loginForm').click()
        //     }
        //     else{
        //         cy.wait(1000)
        //         cy.get('#trainingBtn').click()
        //     }
        // })

        // cy.get('#email').type('1@1.1')
        // cy.get('#password').type('212121')
        // cy.wait(1000)
        // cy.get('#loginForm').click()
        // cy.wait(2000)

        cy.wait(2000)
        cy.contains(' Login').click()
        cy.wait(1000)
        cy.get('#email').type('1@1.1')
        cy.get('#password').type('212121')
        cy.wait(1000)
        cy.get('#loginForm').click()
        cy.wait(3000)
        if(cy.contains(' Logout')){
            cy.contains(' Logout').click()
            cy.wait(2000)
        }
        
        // cy.contains(' Login').click()
        // cy.wait(1000)
        cy.get('#email').type('1@1.1')
        cy.get('#password').type('212121')
        cy.wait(1000)
        cy.get('#loginForm').click()
        cy.wait(2000)
    })

    it('Add Exercise', ()=>{
        cy.get('#trainingBtn').click()
        cy.wait(3000)
        cy.get('#addExBtn').click()
        cy.wait(3000)
        cy.get('#formGridName').select('Plank')
        cy.wait(100)
        cy.get('#formGridDuration').type('1111')
        cy.wait(100)
        cy.get('#formGridPosition').type('TEST-CYPRES')
        cy.wait(1000)
        cy.get('.btn-success').click()
        cy.wait(2000)
        cy.get('.btn-info').click()
    })

    it('Edit Exercise', ()=>{
        cy.wait(3000)
        cy.contains('TEST-CYPRES').parents('tr').find('a').click()
        cy.wait(2000)
        cy.get('#formGridName').select('Skipping')
        cy.wait(100)
        cy.get('#formGridDuration').clear().type('222')
        cy.wait(100)
        cy.get('#formGridPosition').clear().type('TEST-EDIT')
        cy.wait(1000)
        cy.get('.btn-success').click()
        cy.wait(2000)
        cy.get('.btn-info').click()
        cy.wait(3000)
    })

    it('Delete Exercise', ()=>{
        cy.contains('TEST-EDIT').parents('tr').find('button').click()
        cy.wait(3000)
    })

    it('Logout', ()=>{
        cy.contains(' Logout').click()
        cy.wait(3000)
    })
});