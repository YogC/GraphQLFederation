This repository is a GraphQL demo of using the DGS framework together with Apollo Federation Server.

Documentation for the DGS framework can be found [here](https://netflix.github.io/dgs).

The repository contains three separate projects:
1) Policy :  Spring Boot using DGS framework
2) Claim :  Spring Boot using DGS framework
3) Participants :  Spring Boot using DGS framework
4) FileNotes :  Spring Boot using DGS framework
5) Customer :  Spring Boot using DGS framework
6) Vehicle :  Spring Boot using DGS framework
7) apollo-gateway: An instance of Apollo Server acting as the Federated Gateway

Running the demo:
1) Start Policy by running the Spring Boot app from the IDE
2) Start Claim by running the Spring Boot app from the IDE
3) Start Participants by running the Spring Boot app from the IDE
4) Start FileNotes by running the Spring Boot app from the IDE
5) Start Vehicle by running the Spring Boot app from the IDE
6) Run npm install in the apollo-gateway project
7) Run node index.js in the apollo-gateway project
8) Open http://localhost:4000 for the query editor

The following is a federated query that should work.

1) All Data for Policy 1
query  {
  retrievePolicy(policyId: "1") {
    lineOfBusiness,
    retrieveClaims{
      claimId,
      claimNumber,
      participants {
        participantRole,
        customerId,
        participantIdentifier,
        readCustomer {
          customerAddress {
            address,
            state,
            zip
          },
          customerEmail {
            customerEmailIdentifier
          },
          customerName {
            fullName
          },
          customerPhone {
            number
          }
        },
        vehicles {
          vehicleMake,
          vehicleModel,
          vehicleYear
        },
        fileNotes {
          fileNoteDetail
        }
      },
      vehicles {
        vehicleMake
      },
      fileNotesClaim {
        fileNoteDetail
      }
    }
  }
}

2) Data for Policy 1 and Participant Id 1
  query($participantIdentifier: ID!)  {
  retrievePolicy(policyId: "1") {
    lineOfBusiness,
    retrieveClaims{
      claimId,
      claimNumber,
      retrieveParticipant(participantIdentifier: "1") {
        
        participantRole,
        customerId,
        participantIdentifier,
        readCustomer {,

          customerAddress {
            address,
            state,
            zip
          },
          customerEmail {
            customerEmailIdentifier
          },
          customerName {
            fullName
          },
          customerPhone {
            number
          }
        },
        vehicles {
          vehicleMake,
          vehicleModel,
          vehicleYear,
          participantIdentifier
        },
        fileNotes {
          fileNoteDetail,
          participantIdentifier
        }
      }
      
    }
    
  }
}
