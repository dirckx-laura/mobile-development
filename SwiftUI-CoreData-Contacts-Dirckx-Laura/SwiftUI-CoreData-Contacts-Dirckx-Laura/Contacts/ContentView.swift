//
//  ContentView.swift
//  Contacts
//
//  Created by Philippe Possemiers on 03/12/2019.
//  Copyright © 2019 Philippe Possemiers. All rights reserved.
//

import SwiftUI

struct ContentView: View {
	
    @EnvironmentObject var dataSource: DataSource
    @State var showAdd = false
    	
    var body: some View {
		
        VStack {
			
			Text("All Contacts")
            
            
            NavigationView {
                    List(self.dataSource.getAllContacts()!, id:\.self) {
                        contact in NavigationLink(destination: DeleteContactView( contact: contact, showAdd: true)) {
                            Text("\(contact.firstName), \(contact.lastName)")
                        }
                    }
                
            }
        
            
            Button(action: {
                self.showAdd = true
            }) {
                Text("Add contact")
            }
            .sheet(isPresented: $showAdd){
                AddContactView(showAdd: self.$showAdd)
            }
            .padding()
           
			
		}
	}
}

struct AddContactView: View {
    
    @Binding var showAdd: Bool
    @EnvironmentObject var dataSource: DataSource
        @State var firstName: String = ""
      @State var lastName: String = ""
      @State var telephone: String = ""
        
      var body: some View {
        
        VStack {
            
            Form {
                Section(header: Text("Contact Info")) {
                    TextField("First Name", text: $firstName)
                    TextField("Last Name", text: $lastName)
                    TextField("Telephone", text: $telephone)
                }
                
                Section {
                    Button(action: {
                        self.dataSource.addContact(firstName: self.firstName, lastName: self.lastName, telephone: self.telephone)
                        self.showAdd = false
                    }) {
                        Text("Add")
                    }
                }
            }
        }
      }
    }

struct DeleteContactView: View {
     let contact: Contact
    @EnvironmentObject var dataSource: DataSource
    @State var showAdd = false
    
     var body: some View {
        VStack{
            
            Text("\(contact.firstName), \(contact.lastName)")
            
           
            
            Button(action: {
                guard let number = URL(string: "tel://" + contact.telephone) else { return }
                UIApplication.shared.open(number)
            }) {
                Text("\(contact.telephone)")
            }
            
           
            
            Button(action: {
                self.dataSource.deleteContact(firstName: contact.firstName, lastName: contact.lastName, telephone: contact.telephone)
                self.showAdd = false
               
               
                
            }) {
                Text("Delete")
            }
            
            
        }
    }
    
}



struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		/*@START_MENU_TOKEN@*/Text("Hello, World!")/*@END_MENU_TOKEN@*/
	}
    
}




