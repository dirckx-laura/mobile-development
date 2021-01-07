//
//  DataSource.swift
//  contact
//
//  Created by Mobile Development on 06/01/2021.
//

import Foundation
import CoreData
import SwiftUI

class DataSource: ObservableObject {
    
    @Published var changed = false
    
    private var managedContext:NSManagedObjectContext?
    
    init() {
        if let appDelegate = UIApplication.shared.delegate as? AppDelegate {
                    managedContext = appDelegate.persistentContainer.viewContext
                }
    }
    
    func getAllContacts() -> [Contact]? {
        var contacts: [Contact] = []
        guard let context = managedContext else {return nil}
        
        let request = NSFetchRequest<Contact>(entityName: "Contact")
        request.sortDescriptors = [NSSortDescriptor(key: "lastName", ascending: true)]
        
        if let fetchedContacts = try? context.fetch(request) {
            if fetchedContacts.count > 0 {
                contacts = fetchedContacts
            }
        }
        return contacts
        
    }
    
    func getContact(firstname:String, lastname:String, telephone:String) -> Contact?{
        guard let context = managedContext else {return nil}
        
        let request = NSFetchRequest<Contact>(entityName: "Contact")
        request.predicate = NSPredicate(format: "firstName == %@ and lastName == %@ and telephone == %@", firstname, lastname, telephone)
        
        if let contacts = try? context.fetch(request) {
            if contacts.count > 0 {
                return contacts[0]
            }
        }
        return nil
        
        
    }
    
   func addContact(firstName:String,lastName:String,telephone: String){
       guard let context = managedContext else {return}
        
    if let record = getContact(firstname:firstName,lastname:lastName,telephone:telephone){
            print("contact \(record) already exists")
            return
        }
    
    let entity = NSEntityDescription.entity(forEntityName: "Contact", in: context)!
    
    let contact = NSManagedObject(entity: entity, insertInto: context)
    contact.setValue(firstName, forKeyPath: "firstName")
    contact.setValue(lastName, forKeyPath: "lastName")
    contact.setValue(telephone, forKeyPath: "telephone")
    
    do {
        try context.save()
    }
    catch let error as NSError{
        print("could not save. \(error), \(error.userInfo)")
    }
    self.changed = true
    
     
    }
    
    func deleteContact(firstName:String,lastName:String,telephone: String){
        guard let context = managedContext else {return}
         
     if let record = getContact(firstname:firstName,lastname:lastName,telephone:telephone){
             context.delete(record)
         }
        
        do {
            try context.save()
        }
        catch let error as NSError{
            print("could not save. \(error), \(error.userInfo)")
        }
        
        
     
     
     self.changed = true
        
    }
    

}
