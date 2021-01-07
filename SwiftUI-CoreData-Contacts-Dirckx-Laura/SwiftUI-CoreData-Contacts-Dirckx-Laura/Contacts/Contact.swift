//
//  Contact.swift
//  Contacts
//
//  Created by Philippe Possemiers on 03/12/2019.
//  Copyright © 2019 Philippe Possemiers. All rights reserved.
//

import Foundation
import CoreData

public class Contact: NSManagedObject {
	@NSManaged public var firstName: String
	@NSManaged public var lastName: String
	@NSManaged public var telephone: String
}
