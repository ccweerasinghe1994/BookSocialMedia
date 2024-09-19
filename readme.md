# 📖📗📘📙🔖Book Social Media 📖📗📘📙🔖
- [📖📗📘📙🔖Book Social Media 📖📗📘📙🔖](#book-social-media-)
- [📗pom.xml](#pomxml)
  - [**1. Overview of `pom.xml`**](#1-overview-of-pomxml)
    - [**Key Sections:**](#key-sections)
  - [**2. Detailed Breakdown**](#2-detailed-breakdown)
    - [**2.1. Project Coordinates**](#21-project-coordinates)
    - [**2.2. Parent**](#22-parent)
    - [**2.3. Properties**](#23-properties)
    - [**2.4. Dependencies**](#24-dependencies)
      - [**2.4.1. Spring Boot Starters**](#241-spring-boot-starters)
      - [**2.4.2. Development Tools**](#242-development-tools)
      - [**2.4.3. JSON Web Tokens (JWT)**](#243-json-web-tokens-jwt)
      - [**2.4.4. API Documentation**](#244-api-documentation)
      - [**2.4.5. Testing Dependencies**](#245-testing-dependencies)
    - [**2.5. Build Configuration**](#25-build-configuration)
      - [**2.5.1. Spring Boot Maven Plugin**](#251-spring-boot-maven-plugin)
  - [**3. Putting It All Together: Example Workflow**](#3-putting-it-all-together-example-workflow)
    - [**3.1. Building the Project**](#31-building-the-project)
    - [**3.2. Running the Application**](#32-running-the-application)
    - [**3.3. Testing the Application**](#33-testing-the-application)
  - [**4. Advanced Topics and Best Practices**](#4-advanced-topics-and-best-practices)
    - [**4.1. Dependency Management**](#41-dependency-management)
    - [**4.2. Profiles**](#42-profiles)
    - [**4.3. Plugin Management**](#43-plugin-management)
    - [**4.4. Spring Boot Configuration**](#44-spring-boot-configuration)
    - [**4.5. Lombok Best Practices**](#45-lombok-best-practices)
    - [**4.6. Security Considerations**](#46-security-considerations)
  - [**5. Sample Project Structure**](#5-sample-project-structure)
  - [**6. Additional Recommendations**](#6-additional-recommendations)
    - [**6.1. Managing Secrets**](#61-managing-secrets)
    - [**6.2. Dockerization**](#62-dockerization)
    - [**6.3. Continuous Integration/Continuous Deployment (CI/CD)**](#63-continuous-integrationcontinuous-deployment-cicd)
    - [**6.4. API Documentation and Testing**](#64-api-documentation-and-testing)
    - [**6.5. Exception Handling**](#65-exception-handling)
    - [**6.6. Logging**](#66-logging)
  - [**7. Conclusion**](#7-conclusion)
- [📗adding docker compose to the project](#adding-docker-compose-to-the-project)
  - [**Table of Contents**](#table-of-contents)
  - [**1. Overview of Docker Compose**](#1-overview-of-docker-compose)
  - [**2. Detailed Breakdown of `docker-compose.yml`**](#2-detailed-breakdown-of-docker-composeyml)
    - [**2.1. Services**](#21-services)
      - [**2.1.1. PostgreSQL Service**](#211-postgresql-service)
      - [**Common Issues \& Troubleshooting:**](#common-issues--troubleshooting)
      - [**Example Commands:**](#example-commands)
      - [**Additional Configuration Options:**](#additional-configuration-options)
      - [**Security Considerations:**](#security-considerations)
      - [**Full PostgreSQL Service Example with Enhancements:**](#full-postgresql-service-example-with-enhancements)
      - [**Further Reading:**](#further-reading)
      - [**2.1.2. MailDev Service**](#212-maildev-service)
      - [**Common Issues \& Troubleshooting:**](#common-issues--troubleshooting-1)
      - [**Additional Configuration Options:**](#additional-configuration-options-1)
    - [**2.2. Networks**](#22-networks)
    - [**2.3. Volumes**](#23-volumes)
      - [**Additional Volume Configuration Options:**](#additional-volume-configuration-options)
      - [**Security Considerations:**](#security-considerations-1)
  - [**3. Integration with Spring Boot Application**](#3-integration-with-spring-boot-application)
    - [**3.1. Database Configuration**](#31-database-configuration)
    - [**3.2. Mail Configuration**](#32-mail-configuration)
    - [**3.3. Application Startup Dependencies**](#33-application-startup-dependencies)
  - [**4. Running the Docker Compose Setup**](#4-running-the-docker-compose-setup)
    - [**4.1. Prerequisites**](#41-prerequisites)
    - [**4.2. Starting the Services**](#42-starting-the-services)
    - [**4.3. Stopping the Services**](#43-stopping-the-services)
    - [**4.4. Rebuilding Services**](#44-rebuilding-services)
    - [**4.5. Managing Services**](#45-managing-services)
  - [**5. Advanced Configurations and Best Practices**](#5-advanced-configurations-and-best-practices)
    - [**5.1. Environment Variables and `.env` Files**](#51-environment-variables-and-env-files)
    - [**5.2. Health Checks**](#52-health-checks)
    - [**5.3. Using Docker Networks for Multiple Environments**](#53-using-docker-networks-for-multiple-environments)
    - [**5.4. Volume Backups and Restoration**](#54-volume-backups-and-restoration)
    - [**5.5. Securing Services**](#55-securing-services)
    - [**5.6. Optimizing Dockerfile for Spring Boot Application**](#56-optimizing-dockerfile-for-spring-boot-application)
    - [**5.7. Health Checks for MailDev Service**](#57-health-checks-for-maildev-service)
    - [**5.8. Scaling Services**](#58-scaling-services)
    - [**5.9. Using a Reverse Proxy (e.g., Nginx) for Load Balancing**](#59-using-a-reverse-proxy-eg-nginx-for-load-balancing)
    - [**5.10. Environment-Specific Configurations**](#510-environment-specific-configurations)
    - [**5.11. Monitoring and Logging**](#511-monitoring-and-logging)
    - [**5.12. Handling Data Migrations**](#512-handling-data-migrations)
    - [**5.13. Using Docker Secrets for Sensitive Data (Advanced)**](#513-using-docker-secrets-for-sensitive-data-advanced)
    - [**5.14. Optimizing Docker Images for Faster Builds**](#514-optimizing-docker-images-for-faster-builds)
    - [**5.15. Handling Service Dependencies**](#515-handling-service-dependencies)
    - [**5.16. Scaling and Load Balancing**](#516-scaling-and-load-balancing)
    - [**3.2. Configuring Spring Boot to Use MailDev for Email Testing**](#32-configuring-spring-boot-to-use-maildev-for-email-testing)
  - [**4. Running the Docker Compose Setup**](#4-running-the-docker-compose-setup-1)
    - [**4.1. Prerequisites**](#41-prerequisites-1)
    - [**4.2. Building and Running Services**](#42-building-and-running-services)
    - [**4.3. Stopping and Removing Services**](#43-stopping-and-removing-services)
    - [**4.4. Rebuilding Services**](#44-rebuilding-services-1)
    - [**4.5. Scaling Services**](#45-scaling-services)
  - [**5. Advanced Configurations and Best Practices**](#5-advanced-configurations-and-best-practices-1)
    - [**5.1. Using `.env` Files for Environment Variables**](#51-using-env-files-for-environment-variables)
    - [**5.2. Implementing Health Checks**](#52-implementing-health-checks)
    - [**5.3. Securing Sensitive Information**](#53-securing-sensitive-information)
    - [**5.4. Implementing Logging and Monitoring**](#54-implementing-logging-and-monitoring)
    - [**5.5. Automating Backups**](#55-automating-backups)
    - [**5.6. Optimizing Docker Images**](#56-optimizing-docker-images)
    - [**5.7. Using Docker Compose Overrides for Development**](#57-using-docker-compose-overrides-for-development)
    - [**5.8. Leveraging Docker Compose Commands**](#58-leveraging-docker-compose-commands)
    - [**5.9. Managing Data with Docker Volumes**](#59-managing-data-with-docker-volumes)
    - [**5.10. Testing the Setup**](#510-testing-the-setup)
    - [**5.11. Optimizing Docker Compose for Production**](#511-optimizing-docker-compose-for-production)
    - [**5.12. Using External Services and Integrations**](#512-using-external-services-and-integrations)
  - [**6. Conclusion**](#6-conclusion)
- [📗application.yml files](#applicationyml-files)
  - [**Table of Contents**](#table-of-contents-1)
  - [**1. Overview of Spring Boot Configuration**](#1-overview-of-spring-boot-configuration)
  - [**2. Understanding `application.yml`**](#2-understanding-applicationyml)
    - [**2.1. Spring Profiles**](#21-spring-profiles)
    - [**2.2. Servlet Multipart Configuration**](#22-servlet-multipart-configuration)
    - [**2.3. SpringDoc OpenAPI Configuration**](#23-springdoc-openapi-configuration)
    - [**2.4. Server Configuration**](#24-server-configuration)
  - [**3. Understanding `application-dev.yml`**](#3-understanding-application-devyml)
    - [**3.1. DataSource Configuration**](#31-datasource-configuration)
    - [**3.2. JPA (Hibernate) Configuration**](#32-jpa-hibernate-configuration)
    - [**3.3. Mail Configuration**](#33-mail-configuration)
  - [**4. Integration with Docker Compose**](#4-integration-with-docker-compose)
    - [**4.1. PostgreSQL Integration**](#41-postgresql-integration)
    - [**4.2. MailDev Integration**](#42-maildev-integration)
  - [**5. Practical Examples and Usage**](#5-practical-examples-and-usage)
    - [**5.1. Switching Between Profiles**](#51-switching-between-profiles)
    - [**5.2. Handling File Uploads**](#52-handling-file-uploads)
    - [**5.3. Accessing API Documentation**](#53-accessing-api-documentation)
    - [**5.4. Sending Emails During Development**](#54-sending-emails-during-development)
  - [**6. Best Practices and Recommendations**](#6-best-practices-and-recommendations)
    - [**6.1. Securely Managing Secrets**](#61-securely-managing-secrets)
    - [**6.2. Externalizing Configuration**](#62-externalizing-configuration)
    - [**6.3. Optimizing Performance**](#63-optimizing-performance)
    - [**6.4. Comprehensive Logging**](#64-comprehensive-logging)
  - [**7. Conclusion**](#7-conclusion-1)
  - [**8. Additional Resources**](#8-additional-resources)
- [📗Authentication Flow](#authentication-flow)
    - [Flow Breakdown:](#flow-breakdown)
    - [`pom.xml` Correlation:](#pomxml-correlation)
    - [Example Code Snippets:](#example-code-snippets)
    - [Conclusion:](#conclusion)
- [📗User,Token and Role Entities](#usertoken-and-role-entities)
  - [1. **Main Application Class**](#1-main-application-class)
    - [**Code Overview**](#code-overview)
    - [**Explanation**](#explanation)
    - [**Example**](#example)
  - [2. **Role Entity**](#2-role-entity)
    - [**Code Overview**](#code-overview-1)
    - [**Explanation**](#explanation-1)
    - [**Example Usage**](#example-usage)
  - [3. **Empty Code Block**](#3-empty-code-block)
    - [**Explanation**](#explanation-2)
  - [4. **Token Entity**](#4-token-entity)
    - [**Code Overview**](#code-overview-2)
    - [**Explanation**](#explanation-3)
    - [**Example Usage**](#example-usage-1)
  - [5. **User Entity**](#5-user-entity)
    - [**Code Overview**](#code-overview-3)
    - [**Explanation**](#explanation-4)
    - [**Potential Issues and Recommendations**](#potential-issues-and-recommendations)
    - [**Example Usage**](#example-usage-2)
  - [6. **Interconnections and Application Flow**](#6-interconnections-and-application-flow)
    - [**Entity Relationships**](#entity-relationships)
    - [**Application Flow Example**](#application-flow-example)
    - [**Code Integration Example**](#code-integration-example)
  - [7. **Security Configuration**](#7-security-configuration)
  - [8. **Database Schema Representation**](#8-database-schema-representation)
  - [9. **Conclusion and Best Practices**](#9-conclusion-and-best-practices)
- [📗Repository Layer for Role, Token and User Entities](#repository-layer-for-role-token-and-user-entities)
  - [Table of Contents](#table-of-contents-2)
  - [1. Understanding Spring Data JPA Repositories](#1-understanding-spring-data-jpa-repositories)
    - [What is Spring Data JPA?](#what-is-spring-data-jpa)
    - [What is `JpaRepository`?](#what-is-jparepository)
    - [Benefits of Using Spring Data JPA Repositories](#benefits-of-using-spring-data-jpa-repositories)
  - [2. RoleRepository](#2-rolerepository)
    - [Code Overview](#code-overview-4)
    - [Detailed Explanation](#detailed-explanation)
    - [Example Usage](#example-usage-3)
      - [Role Service Example](#role-service-example)
      - [Usage in a Controller](#usage-in-a-controller)
      - [Explanation of Example Usage](#explanation-of-example-usage)
    - [Practical Example](#practical-example)
  - [3. TokenRepository](#3-tokenrepository)
    - [Code Overview](#code-overview-5)
    - [Detailed Explanation](#detailed-explanation-1)
    - [Example Usage](#example-usage-4)
      - [Token Service Example](#token-service-example)
      - [Usage in a Registration Flow](#usage-in-a-registration-flow)
      - [Explanation of Example Usage](#explanation-of-example-usage-1)
  - [4. UserRepository](#4-userrepository)
    - [Code Overview](#code-overview-6)
    - [Detailed Explanation](#detailed-explanation-2)
    - [Example Usage](#example-usage-5)
      - [User Service Example](#user-service-example)
      - [Explanation of Example Usage](#explanation-of-example-usage-2)
    - [Practical Example](#practical-example-1)
  - [5. Integration with Services and Controllers](#5-integration-with-services-and-controllers)
    - [User Registration Flow](#user-registration-flow)
    - [Email Verification Flow](#email-verification-flow)
    - [Example Controller for Authentication](#example-controller-for-authentication)
  - [6. Best Practices and Advanced Features](#6-best-practices-and-advanced-features)
    - [Custom Query Methods](#custom-query-methods)
    - [Handling Optional Results](#handling-optional-results)
    - [Transactions](#transactions)
    - [Pagination and Sorting](#pagination-and-sorting)
    - [Custom Queries with JPQL or Native SQL](#custom-queries-with-jpql-or-native-sql)
    - [Projections and DTOs](#projections-and-dtos)
    - [Indexing and Performance Optimization](#indexing-and-performance-optimization)
    - [Exception Handling](#exception-handling)
    - [Unit and Integration Testing](#unit-and-integration-testing)
  - [7. Conclusion](#7-conclusion-2)
  - [📗register endpoint](#register-endpoint)
  - [**Table of Contents**](#table-of-contents-3)
  - [1. **Project Dependencies**](#1-project-dependencies)
    - [**Code Overview**](#code-overview-7)
    - [**Explanation**](#explanation-5)
    - [**Version Alignment**](#version-alignment)
    - [**Additional Dependencies to Consider**](#additional-dependencies-to-consider)
  - [2. **Authentication Controller**](#2-authentication-controller)
    - [**Code Overview**](#code-overview-8)
    - [**Explanation**](#explanation-6)
    - [**Example Usage**](#example-usage-6)
  - [3. **Authentication Service**](#3-authentication-service)
    - [**Code Overview**](#code-overview-9)
    - [**Explanation**](#explanation-7)
    - [**Expected Functionality**](#expected-functionality)
    - [**Example Implementation**](#example-implementation)
    - [**Practical Example**](#practical-example-2)
  - [4. **Registration Request DTO**](#4-registration-request-dto)
    - [**Code Overview**](#code-overview-10)
    - [**Explanation**](#explanation-8)
    - [**Example Usage**](#example-usage-7)
  - [5. **Security Configuration**](#5-security-configuration)
    - [**Code Overview**](#code-overview-11)
    - [**Explanation**](#explanation-9)
    - [**Example Configuration Flow**](#example-configuration-flow)
    - [**Practical Example**](#practical-example-3)
  - [6. **JWT Filter**](#6-jwt-filter)
    - [**Code Overview**](#code-overview-12)
    - [**Explanation**](#explanation-10)
    - [**Practical Example**](#practical-example-4)
    - [**Potential Improvements and Considerations**](#potential-improvements-and-considerations)
  - [7. **User Details Service Implementation**](#7-user-details-service-implementation)
    - [**Code Overview**](#code-overview-13)
    - [**Explanation**](#explanation-11)
    - [**Practical Example**](#practical-example-5)
    - [**Integration with Spring Security**](#integration-with-spring-security)
  - [8. **Beans Configuration**](#8-beans-configuration)
    - [**Code Overview**](#code-overview-14)
    - [**Explanation**](#explanation-12)
    - [**Practical Example**](#practical-example-6)
    - [**Customization Options**](#customization-options)
  - [9. **JWT Service**](#9-jwt-service)
    - [**Code Overview**](#code-overview-15)
    - [**Explanation**](#explanation-13)
    - [**Potential Issues and Corrections**](#potential-issues-and-corrections)
    - [**Complete Corrected `JwtService` Example**](#complete-corrected-jwtservice-example)
  - [10. **Application Configuration (`application.yml`)**](#10-application-configuration-applicationyml)
    - [**Code Overview**](#code-overview-16)
    - [**Explanation**](#explanation-14)
      - [**1. Spring Datasource Configuration**](#1-spring-datasource-configuration)
      - [**2. JPA Configuration**](#2-jpa-configuration)
      - [**3. Mail Configuration**](#3-mail-configuration)
      - [**4. JWT Security Configuration**](#4-jwt-security-configuration)
  - [11. **Integration and Workflow**](#11-integration-and-workflow)
    - [**1. User Registration Flow**](#1-user-registration-flow)
    - [**2. Email Verification Flow**](#2-email-verification-flow)
    - [**3. User Authentication Flow**](#3-user-authentication-flow)
    - [**4. Accessing Secured Endpoints**](#4-accessing-secured-endpoints)
  - [12. **Best Practices and Recommendations**](#12-best-practices-and-recommendations)
    - [**1. Secure Secret Key Management**](#1-secure-secret-key-management)
    - [**2. Password Security**](#2-password-security)
    - [**3. JWT Token Management**](#3-jwt-token-management)
    - [**4. Exception Handling in JWT Filter**](#4-exception-handling-in-jwt-filter)
    - [**5. Logging and Monitoring**](#5-logging-and-monitoring)
    - [**6. Secure CORS Configuration**](#6-secure-cors-configuration)
    - [**7. Method-Level Security**](#7-method-level-security)
    - [**8. API Documentation**](#8-api-documentation)
    - [**9. Database Migrations**](#9-database-migrations)
    - [**10. Testing**](#10-testing)
    - [**11. Exception Handling and Global Error Responses**](#11-exception-handling-and-global-error-responses)
    - [**12. Ensuring HTTPS**](#12-ensuring-https)
  - [13. **Conclusion**](#13-conclusion)
    - [**Key Takeaways and Best Practices**:](#key-takeaways-and-best-practices)
- [Implementing the Enail Sender Service](#implementing-the-enail-sender-service)
  - [Overview](#overview)
  - [Package and Imports](#package-and-imports)
    - [Explanation](#explanation-15)
    - [Example](#example-1)
  - [Class Declaration and Annotations](#class-declaration-and-annotations)
    - [Explanation](#explanation-16)
    - [Example](#example-2)
  - [Dependencies](#dependencies)
    - [Explanation](#explanation-17)
    - [Example](#example-3)
  - [The `register` Method](#the-register-method)
    - [Explanation](#explanation-18)
    - [Example](#example-4)
  - [The `sendValidationEmail` Method](#the-sendvalidationemail-method)
    - [Explanation](#explanation-19)
    - [Example](#example-5)
  - [The `generateAndSaveActivationToken` Method](#the-generateandsaveactivationtoken-method)
    - [Explanation](#explanation-20)
    - [Example](#example-6)
  - [The `generateActivationCode` Method](#the-generateactivationcode-method)
    - [Explanation](#explanation-21)
    - [Example](#example-7)
  - [Putting It All Together: Registration Flow Example](#putting-it-all-together-registration-flow-example)
    - [Scenario](#scenario)
    - [Example Flow Diagram](#example-flow-diagram)
  - [Security Considerations](#security-considerations-2)
  - [Potential Enhancements](#potential-enhancements)
  - [Conclusion](#conclusion-1)
- [Let's Implement the Email Service](#lets-implement-the-email-service)
  - [🛠️ **Table of Contents**](#️-table-of-contents)
  - [📋 **1. Application Overview**](#-1-application-overview)
  - [🔍 **2. Detailed Code Analysis**](#-2-detailed-code-analysis)
    - [1. `EmailService` Class](#1-emailservice-class)
    - [2. `EmailTemplateName` Enum](#2-emailtemplatename-enum)
    - [3. `AuthenticationController` Class](#3-authenticationcontroller-class)
    - [4. `AuthenticationService` Class](#4-authenticationservice-class)
    - [5. `BookNetworkApplication` Class](#5-booknetworkapplication-class)
    - [6. `activate_account.html` Template](#6-activate_accounthtml-template)
    - [7. `application.yml` Configuration](#7-applicationyml-configuration)
  - [🧐 **3. Understanding the Validation Error**](#-3-understanding-the-validation-error)
  - [⚠️ **4. Potential Issues and Solutions**](#️-4-potential-issues-and-solutions)
    - [A. **Email Validation Failure**](#a-email-validation-failure)
    - [B. **Redundant Validation Annotations**](#b-redundant-validation-annotations)
    - [C. **JSON Deserialization Issues**](#c-json-deserialization-issues)
    - [D. **Exception Handling and Feedback**](#d-exception-handling-and-feedback)
  - [💡 **5. Best Practices and Recommendations**](#-5-best-practices-and-recommendations)
    - [1. **Streamline Validation Annotations**](#1-streamline-validation-annotations)
    - [2. **Enhance Exception Handling**](#2-enhance-exception-handling)
    - [3. **Improve Logging Mechanisms**](#3-improve-logging-mechanisms)
    - [4. **Strengthen Client-Side Validations**](#4-strengthen-client-side-validations)
    - [5. **Utilize API Documentation Tools**](#5-utilize-api-documentation-tools)
    - [6. **Secure Password Handling**](#6-secure-password-handling)
  - [🚀 **6. Comprehensive Example Implementation**](#-6-comprehensive-example-implementation)
    - [A. **Revised `RegistrationRequest` Class**](#a-revised-registrationrequest-class)
    - [B. **Updated `AuthenticationController` Class**](#b-updated-authenticationcontroller-class)
    - [C. **Enhanced `AuthenticationService` Class**](#c-enhanced-authenticationservice-class)
    - [D. **Improved `GlobalExceptionHandler` Class**](#d-improved-globalexceptionhandler-class)
  - [🔍 **7. Testing the Registration Flow**](#-7-testing-the-registration-flow)
    - [1. **Valid Registration Request**](#1-valid-registration-request)
    - [2. **Invalid Email Format**](#2-invalid-email-format)
    - [3. **Missing Email Field**](#3-missing-email-field)
    - [4. **Empty Email Field**](#4-empty-email-field)
  - [📈 **8. Conclusion**](#-8-conclusion)
  - [📚 **9. Additional Resources**](#-9-additional-resources)
- [Implemneting /authenticate endpoint](#implemneting-authenticate-endpoint)
  - [📋 1. **Overview**](#-1-overview)
  - [🔐 2. **AuthenticationController**](#-2-authenticationcontroller)
    - [**Purpose**](#purpose)
    - [**Code Breakdown**](#code-breakdown)
    - [**Detailed Explanation**](#detailed-explanation-3)
    - [**Example Usage**](#example-usage-8)
  - [📦 3. **Data Transfer Objects (DTOs)**](#-3-data-transfer-objects-dtos)
    - [a. 📝 **AuthenticationRequest**](#a--authenticationrequest)
      - [**Explanation**](#explanation-22)
    - [b. 📤 **AuthenticationResponse**](#b--authenticationresponse)
      - [**Explanation**](#explanation-23)
    - [**Example**](#example-8)
  - [⚙️ 4. **Configuration Classes**](#️-4-configuration-classes)
    - [**BeansConfig**](#beansconfig)
      - [**Explanation**](#explanation-24)
    - [**Example Usage**](#example-usage-9)
  - [🔑 5. **JWT Service**](#-5-jwt-service)
    - [**JwtService**](#jwtservice)
      - [**Explanation**](#explanation-25)
    - [**Example Usage**](#example-usage-10)
  - [🛡️ 6. **AuthenticationService**](#️-6-authenticationservice)
    - [**AuthenticationService**](#authenticationservice)
      - [**Explanation**](#explanation-26)
    - [**Example Workflow**](#example-workflow)
  - [🧩 7. **Supporting Components**](#-7-supporting-components)
    - [a. 🫘 **BeansConfig**](#a--beansconfig)
    - [b. 🗂️ **Entities and Repositories**](#b-️-entities-and-repositories)
    - [c. 📧 **EmailService**](#c--emailservice)
  - [🔒 8. **Security Configuration**](#-8-security-configuration)
    - [a. 🌱🔐 **Spring Security Integration**](#a--spring-security-integration)
    - [b. 🔄 **JWT-Based Security Flow**](#b--jwt-based-security-flow)
    - [**Example of Secured Endpoint**](#example-of-secured-endpoint)
  - [📚 9. **Best Practices and Considerations**](#-9-best-practices-and-considerations)
    - [a. 🔒 **Password Security**](#a--password-security)
    - [b. 🔑 **JWT Security**](#b--jwt-security)
    - [c. 📩 **Account Activation**](#c--account-activation)
    - [d. 🛠️ **Error Handling**](#d-️-error-handling)
    - [e. 📈 **Scalability and Maintenance**](#e--scalability-and-maintenance)
  - [🚀 10. **Potential Enhancements**](#-10-potential-enhancements)
    - [a. 🔄 **Refresh Tokens**](#a--refresh-tokens)
    - [b. 🔑 **Password Reset Functionality**](#b--password-reset-functionality)
    - [c. 🔒 **Account Locking Mechanism**](#c--account-locking-mechanism)
    - [d. 📝 **Logging and Monitoring**](#d--logging-and-monitoring)
    - [e. 🧪 **Testing**](#e--testing)
  - [🎯 11. **Conclusion**](#-11-conclusion)


# 📗pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.3.3</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.wchamara</groupId>
	<artifactId>book-network</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>book-network</name>
	<description>Social Media App for Book Sharing</description>
	<url/>
	<licenses>
		<license/>
	</licenses>
	<developers>
		<developer/>
	</developers>
	<scm>
		<connection/>
		<developerConnection/>
		<tag/>
		<url/>
	</scm>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-mail</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-api</artifactId>
			<version>0.12.3</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-impl</artifactId>
			<version>0.12.6</version>
		</dependency>
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.3.0</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt-jackson</artifactId>
			<version>0.12.5</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>

```

Certainly! Let's dive deep into the provided Maven `pom.xml` file for your Spring Boot project, **book-network**, a social media app for book sharing. We'll explore each section, explain the purpose of various dependencies and plugins, and provide examples to illustrate how they fit into your project.

---

## **1. Overview of `pom.xml`**

The `pom.xml` (Project Object Model) is the core of a Maven project. It manages project configuration, dependencies, build settings, plugins, and more. Here's the structure of your `pom.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- Project Information and Configuration -->
    <modelVersion>4.0.0</modelVersion>
    <parent>...</parent>
    <groupId>com.wchamara</groupId>
    <artifactId>book-network</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>book-network</name>
    <description>Social Media App for Book Sharing</description>
    <url/>
    <licenses>
        <license/>
    </licenses>
    <developers>
        <developer/>
    </developers>
    <scm>
        <connection/>
        <developerConnection/>
        <tag/>
        <url/>
    </scm>
    <properties>
        <java.version>17</java.version>
    </properties>
    <!-- Dependencies -->
    <dependencies>
        <!-- Various dependencies listed here -->
    </dependencies>
    <!-- Build Configuration -->
    <build>
        <plugins>
            <!-- Plugins configuration here -->
        </plugins>
    </build>
</project>
```

### **Key Sections:**

1. **Project Coordinates**: Uniquely identify your project (`groupId`, `artifactId`, `version`).
2. **Parent**: Inherits configurations from the Spring Boot Starter Parent.
3. **Properties**: Define project-wide properties, such as Java version.
4. **Dependencies**: Libraries your project relies on.
5. **Build**: Configuration for building the project, including plugins.

---

## **2. Detailed Breakdown**

### **2.1. Project Coordinates**

```xml
<groupId>com.wchamara</groupId>
<artifactId>book-network</artifactId>
<version>0.0.1-SNAPSHOT</version>
<name>book-network</name>
<description>Social Media App for Book Sharing</description>
```

- **`groupId`**: Typically follows the reverse domain name convention. Here, `com.wchamara` likely represents your organization's domain.

- **`artifactId`**: The name of the project. `book-network` suggests it's a networking application centered around books.

- **`version`**: Denotes the current version of your project. `0.0.1-SNAPSHOT` indicates an initial development version.

- **`name` & `description`**: Provide human-readable information about your project.

**Example Usage:**

When you build your project, Maven packages it as `book-network-0.0.1-SNAPSHOT.jar`, making it identifiable.

---

### **2.2. Parent**

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.3.3</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

- **Purpose**: Inherits default configurations, dependency management, and plugin settings from the Spring Boot Starter Parent.

- **Benefits**:
    - **Dependency Management**: Automatically manages versions of common dependencies.
    - **Plugin Configuration**: Pre-configured plugins, reducing the need for explicit configurations.
    - **Standardization**: Ensures consistency across Spring Boot projects.

**Example Scenario:**

Without a parent, you'd need to specify versions for all dependencies and plugins. By inheriting from Spring Boot's parent, you simplify `pom.xml` and ensure compatibility.

---

### **2.3. Properties**

```xml
<properties>
    <java.version>17</java.version>
</properties>
```

- **Purpose**: Defines properties used throughout the `pom.xml`. Here, it sets the Java version to 17.

- **Usage**:
    - The Spring Boot parent uses this property to set the compiler plugin's target and source versions.

**Example Configuration:**

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>${java.version}</source>
                <target>${java.version}</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Here, `${java.version}` ensures consistency and easy updates.

---

### **2.4. Dependencies**

Dependencies are external libraries your project needs. Let's examine each one:

```xml
<dependencies>
    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Spring Mail -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>

    <!-- Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Thymeleaf Template Engine -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>

    <!-- Spring Boot Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- Spring Web (MVC) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot DevTools -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>

    <!-- Lombok for Boilerplate Code Reduction -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- JSON Web Token (JWT) APIs -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.12.3</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.12.6</version>
    </dependency>
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.12.5</version>
    </dependency>

    <!-- SpringDoc OpenAPI for API Documentation -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.3.0</version>
    </dependency>

    <!-- Testing Dependencies -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Let's break down each dependency:

---

#### **2.4.1. Spring Boot Starters**

Spring Boot Starters are dependency descriptors that simplify adding commonly used dependencies. They include transitive dependencies required for various functionalities.

1. **`spring-boot-starter-data-jpa`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    ```

    - **Purpose**: Provides Spring Data JPA functionalities, enabling interaction with relational databases using JPA (Java Persistence API).

    - **Transitive Dependencies**:
        - Hibernate (as the default JPA provider)
        - Spring ORM
        - Spring Data Commons

    - **Example Usage**:

      Define an Entity and Repository to interact with the database.

      ```java
      // User.java
      @Entity
      public class User {
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private Long id;

          private String username;
          private String email;
          // Getters and Setters
      }

      // UserRepository.java
      public interface UserRepository extends JpaRepository<User, Long> {
          Optional<User> findByUsername(String username);
      }
      ```

2. **`spring-boot-starter-mail`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mail</artifactId>
    </dependency>
    ```

    - **Purpose**: Simplifies sending emails using JavaMailSender.

    - **Transitive Dependencies**:
        - JavaMail API
        - Spring Framework's mail support

    - **Example Usage**:

      ```java
      @Service
      public class EmailService {
          @Autowired
          private JavaMailSender mailSender;

          public void sendWelcomeEmail(String to, String username) {
              SimpleMailMessage message = new SimpleMailMessage();
              message.setTo(to);
              message.setSubject("Welcome to Book Network!");
              message.setText("Hello " + username + ", welcome to our platform.");
              mailSender.send(message);
          }
      }
      ```

3. **`spring-boot-starter-security`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    ```

    - **Purpose**: Adds Spring Security for authentication and authorization.

    - **Transitive Dependencies**:
        - Spring Security Core
        - Spring Security Config
        - Spring Security Web

    - **Example Usage**:

      Configure security settings.

      ```java
      @Configuration
      @EnableWebSecurity
      public class SecurityConfig extends WebSecurityConfigurerAdapter {
          @Override
          protected void configure(HttpSecurity http) throws Exception {
              http
                  .csrf().disable()
                  .authorizeRequests()
                  .antMatchers("/api/public/**").permitAll()
                  .anyRequest().authenticated()
                  .and()
                  .httpBasic();
          }
      }
      ```

4. **`spring-boot-starter-thymeleaf`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    ```

    - **Purpose**: Integrates Thymeleaf as the templating engine for server-side rendering of HTML.

    - **Transitive Dependencies**:
        - Thymeleaf
        - Spring Template
        - Spring Boot's Thymeleaf integration

    - **Example Usage**:

      Create a Thymeleaf template and controller.

      ```html
      <!-- src/main/resources/templates/home.html -->
      <!DOCTYPE html>
      <html xmlns:th="http://www.thymeleaf.org">
      <head>
          <title>Home</title>
      </head>
      <body>
          <h1 th:text="'Welcome, ' + ${username} + '!'">Welcome!</h1>
      </body>
      </html>
      ```

      ```java
      @Controller
      public class HomeController {
          @GetMapping("/home")
          public String home(Model model) {
              model.addAttribute("username", "John Doe");
              return "home";
          }
      }
      ```

5. **`spring-boot-starter-validation`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    ```

    - **Purpose**: Provides support for bean validation using Hibernate Validator.

    - **Transitive Dependencies**:
        - Hibernate Validator
        - Bean Validation API

    - **Example Usage**:

      Validate request data in controllers.

      ```java
      public class UserRegistrationDto {
          @NotBlank
          private String username;

          @Email
          private String email;

          @NotBlank
          private String password;

          // Getters and Setters
      }

      @RestController
      @RequestMapping("/api/auth")
      public class AuthController {
          @PostMapping("/register")
          public ResponseEntity<String> register(@Valid @RequestBody UserRegistrationDto registrationDto) {
              // Registration logic
              return ResponseEntity.ok("User registered successfully");
          }
      }
      ```

6. **`spring-boot-starter-web`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    ```

    - **Purpose**: Sets up Spring MVC for building web applications, including RESTful services.

    - **Transitive Dependencies**:
        - Spring MVC
        - Jackson (for JSON processing)
        - Embedded Tomcat server

    - **Example Usage**:

      Create a REST controller.

      ```java
      @RestController
      @RequestMapping("/api/books")
      public class BookController {
          @GetMapping
          public List<Book> getAllBooks() {
              // Fetch books from database
              return new ArrayList<>();
          }

          @PostMapping
          public Book createBook(@RequestBody Book book) {
              // Save book to database
              return book;
          }
      }
      ```

---

#### **2.4.2. Development Tools**

1. **`spring-boot-devtools`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    ```

    - **Purpose**: Provides development-time features like automatic restarts, live reload, and enhanced debugging.

    - **Transitive Dependencies**:
        - Spring Boot DevTools

    - **Configuration**:
        - **Scope**: Set to `runtime` to include it only during development.
        - **Optional**: Not included in the final build artifact.

    - **Example Usage**:

      Modify your code, and Spring Boot DevTools automatically restarts the application, reflecting changes without manual restarts.

2. **`lombok`**

    ```xml
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    ```

    - **Purpose**: Reduces boilerplate code by generating getters, setters, constructors, and more using annotations.

    - **Transitive Dependencies**:
        - Lombok

    - **Example Usage**:

      Use Lombok annotations in your entities and DTOs.

      ```java
      @Data
      @Entity
      public class Book {
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private Long id;

          @NotBlank
          private String title;

          @NotBlank
          private String author;

          private String description;
      }
      ```

      The `@Data` annotation generates getters, setters, `toString()`, `equals()`, and `hashCode()` methods.

    - **Note**: IDE support is required for Lombok annotations to be processed correctly. Ensure Lombok plugins are installed in your IDE (e.g., IntelliJ IDEA, Eclipse).

---

#### **2.4.3. JSON Web Tokens (JWT)**

JWT is used for secure token-based authentication.

1. **`jjwt-api`**

    ```xml
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.12.3</version>
    </dependency>
    ```

    - **Purpose**: Provides the API for creating and parsing JWTs.

2. **`jjwt-impl`**

    ```xml
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-impl</artifactId>
        <version>0.12.6</version>
    </dependency>
    ```

    - **Purpose**: Provides the implementation of the JJWT API.

3. **`jjwt-jackson`**

    ```xml
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-jackson</artifactId>
        <version>0.12.5</version>
    </dependency>
    ```

    - **Purpose**: Adds Jackson support for JSON processing within JWTs.

    - **Version Note**: Ensure that the versions of `jjwt-api`, `jjwt-impl`, and `jjwt-jackson` are compatible. Ideally, align their patch versions (e.g., all `0.12.x`).

**Example Usage:**

Generate and validate JWT tokens.

```java
@Component
public class JwtUtil {

    private final String secretKey = "your-secret-key"; // Use a secure key and store it safely

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                   .setSubject(userDetails.getUsername())
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                   .signWith(SignatureAlgorithm.HS256, secretKey)
                   .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                   .setSigningKey(secretKey)
                   .parseClaimsJws(token)
                   .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
```

---

#### **2.4.4. API Documentation**

1. **`springdoc-openapi-starter-webmvc-ui`**

    ```xml
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.3.0</version>
    </dependency>
    ```

    - **Purpose**: Integrates SpringDoc OpenAPI with Spring MVC, automatically generating API documentation and providing a Swagger UI interface.

    - **Transitive Dependencies**:
        - SpringDoc OpenAPI
        - Swagger UI

    - **Example Usage**:

      Access API documentation at `http://localhost:8080/swagger-ui.html` after running the application.

      ```java
      // No additional configuration required for basic setup
      @RestController
      @RequestMapping("/api/books")
      public class BookController {
          // Controller methods
      }
      ```

      SpringDoc automatically scans your controllers and generates the OpenAPI specification.

---

#### **2.4.5. Testing Dependencies**

1. **`spring-boot-starter-test`**

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    ```

    - **Purpose**: Provides a comprehensive set of testing libraries, including JUnit 5, Mockito, Spring Test, AssertJ, and more.

    - **Transitive Dependencies**:
        - JUnit Jupiter (JUnit 5)
        - Mockito
        - Spring Test
        - AssertJ

    - **Example Usage**:

      Write unit tests for your services.

      ```java
      @SpringBootTest
      public class UserServiceTest {

          @Autowired
          private UserService userService;

          @MockBean
          private UserRepository userRepository;

          @Test
          public void testFindUserByUsername() {
              User mockUser = new User();
              mockUser.setUsername("john_doe");
              when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(mockUser));

              User user = userService.findByUsername("john_doe");
              assertNotNull(user);
              assertEquals("john_doe", user.getUsername());
          }
      }
      ```

2. **`spring-security-test`**

    ```xml
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-test</artifactId>
        <scope>test</scope>
    </dependency>
    ```

    - **Purpose**: Provides support for testing Spring Security features, including mock authentication.

    - **Transitive Dependencies**:
        - Spring Security Test utilities

    - **Example Usage**:

      Test secured endpoints with mock users.

      ```java
      @WebMvcTest(BookController.class)
      public class BookControllerTest {

          @Autowired
          private MockMvc mockMvc;

          @Test
          @WithMockUser(username = "admin", roles = {"ADMIN"})
          public void testGetBooksAsAdmin() throws Exception {
              mockMvc.perform(get("/api/books"))
                     .andExpect(status().isOk());
          }

          @Test
          @WithAnonymousUser
          public void testGetBooksAsAnonymous() throws Exception {
              mockMvc.perform(get("/api/books"))
                     .andExpect(status().isUnauthorized());
          }
      }
      ```

---

### **2.5. Build Configuration**

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <excludes>
                    <exclude>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                    </exclude>
                </excludes>
            </configuration>
        </plugin>
    </plugins>
</build>
```

#### **2.5.1. Spring Boot Maven Plugin**

- **Artifact**: `spring-boot-maven-plugin`

- **Purpose**: Facilitates packaging, running, and deploying Spring Boot applications. It can repackage existing JARs with dependencies, run the application, and more.

- **Key Features**:
    - **Repackaging**: Creates an executable JAR or WAR with embedded dependencies.
    - **Run**: Allows you to run the Spring Boot application directly using Maven commands.
    - **Build Information**: Adds build information to the JAR.

- **Configuration Explained**:

    ```xml
    <configuration>
        <excludes>
            <exclude>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
            </exclude>
        </excludes>
    </configuration>
    ```

    - **Excluding Lombok**: Lombok is a compile-time dependency that isn't needed at runtime. Excluding it from the repackaged JAR prevents unnecessary inclusion.

- **Example Usage**:

    - **Packaging the Application**:

        ```bash
        mvn clean package
        ```

      This command cleans the previous build artifacts and packages the application into an executable JAR located in the `target/` directory.

    - **Running the Application**:

        ```bash
        mvn spring-boot:run
        ```

      Starts the Spring Boot application without needing to build the JAR first.

    - **Running the Executable JAR**:

      After packaging, you can run the JAR directly:

        ```bash
        java -jar target/book-network-0.0.1-SNAPSHOT.jar
        ```

---

## **3. Putting It All Together: Example Workflow**

Let's walk through an example workflow using your `pom.xml` to build, run, and test the **book-network** application.

### **3.1. Building the Project**

1. **Compile the Code**:

    ```bash
    mvn compile
    ```

    - Maven compiles the Java source files in `src/main/java`.

2. **Package the Application**:

    ```bash
    mvn clean package
    ```

    - **`clean`**: Removes previous build artifacts.
    - **`package`**: Compiles the code and packages it into a JAR.

   **Result**: An executable JAR named `book-network-0.0.1-SNAPSHOT.jar` in the `target/` directory.

### **3.2. Running the Application**

1. **Using Maven**:

    ```bash
    mvn spring-boot:run
    ```

    - Starts the Spring Boot application.
    - Benefits from DevTools features like automatic restarts on code changes.

2. **Using the Executable JAR**:

    ```bash
    java -jar target/book-network-0.0.1-SNAPSHOT.jar
    ```

    - Runs the packaged application.
    - Suitable for production-like environments.

### **3.3. Testing the Application**

1. **Running Unit Tests**:

    ```bash
    mvn test
    ```

    - Executes tests located in `src/test/java`.
    - Utilizes `spring-boot-starter-test` and `spring-security-test` for comprehensive testing.

2. **Example Test Execution**:

   Suppose you have a `UserServiceTest` as previously shown. Running `mvn test` executes this test along with others, ensuring your services behave as expected.

---

## **4. Advanced Topics and Best Practices**

### **4.1. Dependency Management**

- **Avoid Version Conflicts**: By inheriting from `spring-boot-starter-parent`, many dependency versions are managed for you. Ensure any additional dependencies you add are compatible.

- **Using BOMs**: For managing versions across multiple dependencies, consider using a Bill of Materials (BOM). However, the Spring Boot parent often handles this.

### **4.2. Profiles**

Maven profiles allow you to customize builds for different environments (e.g., development, testing, production).

**Example Configuration:**

```xml
<profiles>
    <profile>
        <id>dev</id>
        <properties>
            <spring.profiles.active>dev</spring.profiles.active>
        </properties>
    </profile>
    <profile>
        <id>prod</id>
        <properties>
            <spring.profiles.active>prod</spring.profiles.active>
        </properties>
    </profile>
</profiles>
```

**Usage:**

```bash
mvn clean package -Pprod
```

Activates the `prod` profile during the build.

### **4.3. Plugin Management**

Beyond the Spring Boot Maven Plugin, you can add other plugins for tasks like code analysis, documentation, and more.

**Example: Adding Checkstyle for Code Quality**

```xml
<build>
    <plugins>
        <!-- Existing plugins -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-checkstyle-plugin</artifactId>
            <version>3.1.2</version>
            <configuration>
                <configLocation>checkstyle.xml</configLocation>
                <encoding>UTF-8</encoding>
                <consoleOutput>true</consoleOutput>
                <failsOnError>true</failsOnError>
            </configuration>
            <executions>
                <execution>
                    <phase>validate</phase>
                    <goals>
                        <goal>check</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

**Usage:**

```bash
mvn checkstyle:check
```

Ensures code adheres to defined style guidelines.

### **4.4. Spring Boot Configuration**

Leverage Spring Boot's externalized configuration features to manage settings via `application.properties` or `application.yml`.

**Example `application.properties`:**

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/booknetwork
spring.datasource.username=root
spring.datasource.password=secret
spring.jpa.hibernate.ddl-auto=update

# Mail Configuration
spring.mail.host=smtp.example.com
spring.mail.port=587
spring.mail.username=your-email@example.com
spring.mail.password=your-email-password

# JWT Configuration
jwt.secret=your-secret-key
jwt.expiration=3600000
```

**Example Usage:**

Inject properties into your Spring components.

```java
@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    // Getters and Setters
}
```

### **4.5. Lombok Best Practices**

- **Use Selective Annotations**: Instead of using `@Data` (which includes setters, getters, `equals`, `hashCode`, `toString`), use more specific annotations like `@Getter`, `@Setter`, or `@Builder` to avoid unintended method generation.

- **Immutable Objects**: Use Lombok's `@Value` for creating immutable classes.

**Example:**

```java
@Value
public class BookDto {
    Long id;
    String title;
    String author;
    String description;
}
```

This generates a final class with private final fields, getters, and a constructor, but no setters.

### **4.6. Security Considerations**

- **Secure JWT Secret**: Never hardcode secrets. Use environment variables or secure vaults.

- **HTTPS**: Ensure your application runs over HTTPS in production.

- **CSRF Protection**: Configure CSRF protection as needed, especially for state-changing operations.

**Example Security Configuration Enhancements:**

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable() // Enable CSRF protection in production
        .authorizeRequests()
        .antMatchers("/api/public/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
}
```

---

## **5. Sample Project Structure**

A typical Spring Boot project structure aligns with Maven's standard directory layout.

```
book-network/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── wchamara/
│   │   │           └── booknetwork/
│   │   │               ├── BookNetworkApplication.java
│   │   │               ├── controller/
│   │   │               │   └── BookController.java
│   │   │               ├── model/
│   │   │               │   └── Book.java
│   │   │               ├── repository/
│   │   │               │   └── BookRepository.java
│   │   │               ├── service/
│   │   │               │   └── BookService.java
│   │   │               └── config/
│   │   │                   └── SecurityConfig.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/
│   │           └── home.html
│   └── test/
│       └── java/
│           └── com/
│               └── wchamara/
│                   └── booknetwork/
│                       └── BookControllerTest.java
├── pom.xml
└── README.md
```

**Key Components:**

- **`BookNetworkApplication.java`**: The entry point of the Spring Boot application.

    ```java
    @SpringBootApplication
    public class BookNetworkApplication {
        public static void main(String[] args) {
            SpringApplication.run(BookNetworkApplication.class, args);
        }
    }
    ```

- **Controllers**: Handle HTTP requests.

    ```java
    @RestController
    @RequestMapping("/api/books")
    public class BookController {
        @Autowired
        private BookService bookService;

        @GetMapping
        public List<Book> getAllBooks() {
            return bookService.findAllBooks();
        }

        @PostMapping
        public Book addBook(@RequestBody Book book) {
            return bookService.saveBook(book);
        }
    }
    ```

- **Models**: Represent the data structure.

    ```java
    @Entity
    public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        private String title;

        @NotBlank
        private String author;

        private String description;

        // Lombok annotations for getters and setters
    }
    ```

- **Repositories**: Interface for database operations.

    ```java
    public interface BookRepository extends JpaRepository<Book, Long> {
        List<Book> findByAuthor(String author);
    }
    ```

- **Services**: Business logic.

    ```java
    @Service
    public class BookService {
        @Autowired
        private BookRepository bookRepository;

        public List<Book> findAllBooks() {
            return bookRepository.findAll();
        }

        public Book saveBook(Book book) {
            return bookRepository.save(book);
        }
    }
    ```

- **Configuration**: Security settings, etc.

    ```java
    @Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // Security configuration
        }
    }
    ```

- **Resources**: Static and template resources.

    - **`application.properties`**: Configuration properties.
    - **`templates/`**: Thymeleaf templates like `home.html`.

- **Tests**: Verify application behavior.

    ```java
    @SpringBootTest
    public class BookControllerTest {
        @Autowired
        private MockMvc mockMvc;

        @Test
        public void testGetAllBooks() throws Exception {
            mockMvc.perform(get("/api/books"))
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$.length()").value(0));
        }
    }
    ```

---

## **6. Additional Recommendations**

### **6.1. Managing Secrets**

Never commit sensitive information (like JWT secrets, database passwords) to version control. Use environment variables or externalized configuration.

**Example: Using Environment Variables**

```properties
# application.properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
jwt.secret=${JWT_SECRET}
```

**Setting Environment Variables:**

On Unix-based systems:

```bash
export DB_URL=jdbc:mysql://localhost:3306/booknetwork
export DB_USERNAME=root
export DB_PASSWORD=secret
export JWT_SECRET=your-secret-key
```

### **6.2. Dockerization**

Containerize your application for consistent deployment environments.

**Dockerfile Example:**

```dockerfile
# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/book-network-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
```

**Building and Running the Docker Image:**

```bash
# Build the Docker image
docker build -t book-network .

# Run the Docker container
docker run -p 8080:8080 book-network
```

### **6.3. Continuous Integration/Continuous Deployment (CI/CD)**

Automate your build, test, and deployment processes using CI/CD tools like GitHub Actions, GitLab CI, Jenkins, etc.

**Example: GitHub Actions Workflow (`.github/workflows/ci.yml`)**

```yaml
name: CI

on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
    - name: Checkout Code
      uses: actions/checkout@v3

    - name: Set up JDK 17
      uses: actions/setup-java@v3
      with:
        java-version: '17'
        distribution: 'adopt'

    - name: Build with Maven
      run: mvn clean package --no-transfer-progress

    - name: Run Tests
      run: mvn test --no-transfer-progress
```

This workflow triggers on pushes and pull requests to the `main` branch, checks out the code, sets up JDK 17, builds the project, and runs tests.

### **6.4. API Documentation and Testing**

Utilize SpringDoc OpenAPI to generate interactive API documentation and integrate API testing tools.

**Accessing Swagger UI:**

After running the application, navigate to `http://localhost:8080/swagger-ui.html` to view and interact with your API documentation.

**Example: Documenting an Endpoint**

```java
@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Operations pertaining to books in Book Network")
public class BookController {

    @Operation(summary = "Retrieve all books", description = "Returns a list of all books")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @Operation(summary = "Add a new book", description = "Creates a new book entry")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Book created successfully")
    })
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
}
```

**Result**: These annotations enhance the generated OpenAPI documentation, providing clear descriptions and response information.

### **6.5. Exception Handling**

Implement global exception handling to manage errors gracefully.

**Example: Global Exception Handler**

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Additional exception handlers
}
```

**Usage**:

When validation fails or a resource isn't found, these handlers return structured error responses.

### **6.6. Logging**

Incorporate logging for monitoring and debugging.

**Example: Using SLF4J with Logback (default in Spring Boot)**

```java
@RestController
@RequestMapping("/api/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @GetMapping
    public List<Book> getAllBooks() {
        logger.info("Fetching all books");
        return bookService.findAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        logger.info("Adding new book: {}", book.getTitle());
        return bookService.saveBook(book);
    }
}
```

**Configuration**: Customize logging levels in `application.properties`.

```properties
logging.level.com.wchamara.booknetwork=DEBUG
```

---

## **7. Conclusion**

Your `pom.xml` is well-structured, leveraging Spring Boot's powerful features and best practices. Here's a recap of key points:

- **Parent POM**: Inherits configurations from Spring Boot, simplifying dependency management.
- **Dependencies**: Comprehensive set covering web, security, data access, validation, mail, JWT, documentation, and testing.
- **Build Plugins**: Spring Boot Maven Plugin facilitates building and running the application.
- **Best Practices**: Externalized configuration, testing, logging, exception handling, and documentation enhance the application's robustness and maintainability.

By understanding each component and how they interrelate, you can effectively manage and scale your **book-network** application. Continue adhering to best practices, keep dependencies updated, and utilize Spring Boot's extensive ecosystem to build a feature-rich social media platform for book sharing.

---

If you have specific areas you'd like to delve deeper into or additional questions about your project setup, feel free to ask!

# 📗adding docker compose to the project

```yaml
services:
  postgres:
    container_name: postgres-sql-bsn
    image: postgres:16
    environment:
      POSTGRES_USER: username
      POSTGRES_PASSWORD: password
      PG_DATA: /var/lib/postgresql/data
      POSTGRES_DB: book_social_network
    volumes:
      - postgres:/data/postgres
    ports:
      - "5432:5432"
    networks:
      - spring-demo
    restart: unless-stopped
  mail-dev:
    container_name: mail-dev-bsn
    image: maildev/maildev
    ports:
      - "1080:1080"
      - "1025:1025"


networks:
  spring-demo:
    driver: bridge


volumes:
  postgres:
    driver: local


```

Certainly! The YAML snippet you provided is a **Docker Compose** configuration file designed to orchestrate and manage multiple Docker containers for your **Book Social Network** application. This setup includes a PostgreSQL database and a MailDev service for handling emails during development. Let's delve deep into each component of this `docker-compose.yml` file, understand its purpose, configuration options, and see how it integrates into your application with practical examples.

---

## **Table of Contents**

1. [Overview of Docker Compose](#1-overview-of-docker-compose)
2. [Detailed Breakdown of `docker-compose.yml`](#2-detailed-breakdown-of-docker-composeyml)
   - [2.1. Services](#21-services)
     - [2.1.1. PostgreSQL Service](#211-postgresql-service)
     - [2.1.2. MailDev Service](#212-maildev-service)
   - [2.2. Networks](#22-networks)
   - [2.3. Volumes](#23-volumes)
3. [Integration with Spring Boot Application](#3-integration-with-spring-boot-application)
4. [Running the Docker Compose Setup](#4-running-the-docker-compose-setup)
5. [Advanced Configurations and Best Practices](#5-advanced-configurations-and-best-practices)
6. [Conclusion](#6-conclusion)

---

## **1. Overview of Docker Compose**

**Docker Compose** is a tool for defining and managing multi-container Docker applications. With a simple YAML file (`docker-compose.yml`), you can configure your application's services, networks, and volumes. This simplifies the process of setting up, running, and scaling applications by allowing you to manage multiple containers with ease.

**Key Benefits:**

- **Simplified Configuration:** Define all services in a single file.
- **Reproducibility:** Ensure consistent environments across development, testing, and production.
- **Scalability:** Easily scale services up or down as needed.
- **Isolation:** Services run in separate containers, preventing conflicts.

In your case, the `docker-compose.yml` file sets up two primary services: a PostgreSQL database and a MailDev service for email testing.

---

## **2. Detailed Breakdown of `docker-compose.yml`**

Let's dissect each part of your `docker-compose.yml` to understand its configuration and role in your application.

### **2.1. Services**

The `services` section defines the different containers that make up your application. Each service represents a container that Docker will manage.

#### **2.1.1. PostgreSQL Service**

```yaml
services:
  postgres:
    container_name: postgres-sql-bsn
    image: postgres:16
    environment:
      POSTGRES_USER: username
      POSTGRES_PASSWORD: password
      PG_DATA: /var/lib/postgresql/data
      POSTGRES_DB: book_social_network
    volumes:
      - postgres:/data/postgres
    ports:
      - "5432:5432"
    networks:
      - spring-demo
    restart: unless-stopped
```

**Explanation:**

1. **Service Name (`postgres`):**
   - This is the identifier for the service within Docker Compose. You can reference this service by its name in other parts of the configuration or when linking services.

2. **Container Name (`postgres-sql-bsn`):**
   - Assigns a custom name to the container instead of the default naming convention (which typically combines the project name and service name). This can be helpful for clarity and management.

3. **Image (`postgres:16`):**
   - Specifies the Docker image to use for the container. Here, it uses PostgreSQL version 16 from Docker Hub.
   - **Example:**
     - Pulls the image from [Docker Hub](https://hub.docker.com/_/postgres).

4. **Environment Variables:**
   - **`POSTGRES_USER`:** Sets the default PostgreSQL user.
   - **`POSTGRES_PASSWORD`:** Sets the password for the PostgreSQL user.
   - **`PG_DATA`:** Specifies the directory where PostgreSQL will store its data. *Note:* The correct environment variable for data directory in PostgreSQL Docker images is `PGDATA` (without an underscore). Ensure consistency to prevent configuration issues.
   - **`POSTGRES_DB`:** Creates a default database named `book_social_network` upon initialization.
   - **Example Usage:**
     ```yaml
     environment:
       POSTGRES_USER: myuser
       POSTGRES_PASSWORD: mypassword
       PGDATA: /var/lib/postgresql/data
       POSTGRES_DB: mydatabase
     ```

5. **Volumes:**
   - **`- postgres:/data/postgres`:**
     - Mounts a Docker-managed volume named `postgres` to the container's `/data/postgres` directory.
     - **Purpose:** Persists PostgreSQL data outside the container, ensuring data isn't lost when the container stops or is removed.
   - **Example Usage:**
     ```yaml
     volumes:
       - postgres:/var/lib/postgresql/data
     ```

6. **Ports:**
   - **`- "5432:5432"`:**
     - Maps port **5432** on the host machine to port **5432** in the container.
     - **Purpose:** Allows your application (running on the host or other containers) to communicate with PostgreSQL via `localhost:5432`.
   - **Example Usage:**
     ```yaml
     ports:
       - "5432:5432"
     ```

7. **Networks:**
   - **`- spring-demo`:**
     - Connects the container to a Docker network named `spring-demo`.
     - **Purpose:** Facilitates communication between containers in the same network using service names as hostnames.
   - **Example Usage:**
     ```yaml
     networks:
       - my-network
     ```

8. **Restart Policy (`restart: unless-stopped`):**
   - **Purpose:** Automatically restarts the container unless it is explicitly stopped.
   - **Other Options:**
     - `no`: Do not automatically restart.
     - `on-failure`: Restart only if the container exits with a non-zero status.
     - `always`: Always restart the container if it stops.
   - **Example Usage:**
     ```yaml
     restart: always
     ```

**Practical Example: Connecting Your Spring Boot Application to PostgreSQL**

In your Spring Boot `application.properties` or `application.yml`, you'd configure the datasource to connect to PostgreSQL.

**`application.properties`:**
```properties
spring.datasource.url=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

**Explanation:**
- **`jdbc:postgresql://postgres-sql-bsn:5432/book_social_network`:**
  - **`postgres-sql-bsn`** is the container name, acting as the hostname within the Docker network `spring-demo`.
  - **Port:** `5432` is the default PostgreSQL port.
  - **Database:** `book_social_network` is the database created via the `POSTGRES_DB` environment variable.

---

#### **Common Issues & Troubleshooting:**
- **Incorrect Environment Variable (`PG_DATA` vs. `PGDATA`):**
  - Ensure the correct variable is used. PostgreSQL Docker images expect `PGDATA` (all uppercase, no underscore).
  - **Fix:**
    ```yaml
    environment:
      PGDATA: /var/lib/postgresql/data
    ```

- **Port Conflicts:**
  - If port `5432` is already in use on the host, you can map to a different host port.
  - **Example:**
    ```yaml
    ports:
      - "15432:5432"
    ```
    - Access PostgreSQL via `localhost:15432`.

---

#### **Example Commands:**
- **Starting the PostgreSQL Service:**
  ```bash
  docker-compose up -d postgres
  ```
- **Accessing PostgreSQL CLI:**
  ```bash
  docker exec -it postgres-sql-bsn psql -U username -d book_social_network
  ```

---

#### **Additional Configuration Options:**
- **Health Checks:**
  - Ensure PostgreSQL is ready before your application starts.
  ```yaml
  healthcheck:
    test: ["CMD-SHELL", "pg_isready -U username"]
    interval: 10s
    timeout: 5s
    retries: 5
  ```
- **Environment Variable for Locale:**
  ```yaml
  environment:
    LANG: en_US.UTF-8
    LC_ALL: en_US.UTF-8
  ```

---

#### **Security Considerations:**
- **Secrets Management:**
  - Avoid hardcoding sensitive information like `POSTGRES_PASSWORD` in the `docker-compose.yml`.
  - **Use `.env` Files:**
    - Create a `.env` file:
      ```env
      POSTGRES_USER=username
      POSTGRES_PASSWORD=password
      POSTGRES_DB=book_social_network
      ```
    - Reference in `docker-compose.yml`:
      ```yaml
      environment:
        POSTGRES_USER: ${POSTGRES_USER}
        POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
        POSTGRES_DB: ${POSTGRES_DB}
      ```
    - **Note:** Ensure `.env` is added to `.gitignore` to prevent committing secrets.

---

#### **Full PostgreSQL Service Example with Enhancements:**
```yaml
services:
  postgres:
    container_name: postgres-sql-bsn
    image: postgres:16
    environment:
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
      PGDATA: /var/lib/postgresql/data
      POSTGRES_DB: ${POSTGRES_DB}
    volumes:
      - postgres:/var/lib/postgresql/data
    ports:
      - "5432:5432"
    networks:
      - spring-demo
    restart: unless-stopped
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U ${POSTGRES_USER}"]
      interval: 10s
      timeout: 5s
      retries: 5
```

---

#### **Further Reading:**
- [Official PostgreSQL Docker Image Documentation](https://hub.docker.com/_/postgres)
- [Docker Compose Networking](https://docs.docker.com/compose/networking/)
- [Managing Secrets in Docker Compose](https://docs.docker.com/compose/environment-variables/#the-env_file-attribute)

---

#### **2.1.2. MailDev Service**

```yaml
mail-dev:
  container_name: mail-dev-bsn
  image: maildev/maildev
  ports:
    - "1080:1080"
    - "1025:1025"
```

**Explanation:**

1. **Service Name (`mail-dev`):**
   - Identifies the MailDev service within Docker Compose.

2. **Container Name (`mail-dev-bsn`):**
   - Assigns a custom name to the MailDev container for clarity.

3. **Image (`maildev/maildev`):**
   - Uses the [MailDev](https://maildev.github.io/maildev/) Docker image.
   - **Purpose:** Provides a simple way to test email sending during development. It acts as a dummy SMTP server and web interface to view sent emails.

4. **Ports:**
   - **`- "1080:1080"`:**
     - Maps port **1080** on the host to port **1080** in the container.
     - **Purpose:** Access the MailDev web interface via `http://localhost:1080`.
   - **`- "1025:1025"`:**
     - Maps port **1025** on the host to port **1025** in the container.
     - **Purpose:** MailDev's SMTP server listens on port `1025`. Configure your application to send emails via `localhost:1025`.
   - **Example Usage:**
     - **Sending Email Configuration in Spring Boot:**
       ```properties
       spring.mail.host=localhost
       spring.mail.port=1025
       spring.mail.username=
       spring.mail.password=
       spring.mail.properties.mail.smtp.auth=false
       spring.mail.properties.mail.smtp.starttls.enable=false
       ```

5. **No Additional Configuration:**
   - MailDev is lightweight and typically doesn't require extensive configuration for development purposes.

**Practical Example: Testing Email Sending in Development**

Suppose your Spring Boot application sends a welcome email upon user registration. With MailDev configured, you can test this functionality without sending real emails.

**Steps:**

1. **Start the MailDev Service:**
   ```bash
   docker-compose up -d mail-dev
   ```

2. **Configure Spring Boot to Use MailDev SMTP:**
   **`application.properties`:**
   ```properties
   spring.mail.host=localhost
   spring.mail.port=1025
   spring.mail.username=
   spring.mail.password=
   spring.mail.properties.mail.smtp.auth=false
   spring.mail.properties.mail.smtp.starttls.enable=false
   ```

3. **Send an Email from Your Application:**
   ```java
   @Service
   public class EmailService {
       @Autowired
       private JavaMailSender mailSender;

       public void sendWelcomeEmail(String to, String username) {
           SimpleMailMessage message = new SimpleMailMessage();
           message.setTo(to);
           message.setSubject("Welcome to Book Network!");
           message.setText("Hello " + username + ", welcome to our platform.");
           mailSender.send(message);
       }
   }
   ```

4. **View Sent Emails:**
   - Navigate to [http://localhost:1080](http://localhost:1080) in your browser.
   - You'll see the sent welcome email listed there.

**Advantages of Using MailDev:**

- **No Real Emails Sent:** Ensures that testing doesn't spam real email addresses.
- **Immediate Feedback:** View sent emails in real-time via the web interface.
- **Lightweight:** Minimal resource usage and easy to set up.

---

#### **Common Issues & Troubleshooting:**

- **Port Conflicts:**
  - Ensure that ports `1080` and `1025` are free on your host machine.
  - **Solution:** Change host ports if conflicts occur.
    ```yaml
    ports:
      - "2080:1080" # Host port 2080 maps to container port 1080
      - "2025:1025" # Host port 2025 maps to container port 1025
    ```
  
- **Application Not Sending Emails:**
  - Verify SMTP configuration in `application.properties`.
  - Ensure that MailDev is running and accessible.
  - Check Docker container logs for MailDev:
    ```bash
    docker logs mail-dev-bsn
    ```

---

#### **Additional Configuration Options:**

- **Persistent Storage:**
  - By default, MailDev doesn't require persistent storage as it only holds emails in memory.
  - However, if you wish to persist emails across container restarts:
    ```yaml
    volumes:
      - maildev-data:/data
    ```
    **Note:** MailDev's default setup doesn't support data persistence, so this might require custom configurations or alternative tools.

- **Custom MailDev Configuration:**
  - Set environment variables to customize MailDev's behavior.
  ```yaml
  environment:
    - MAILDEV_INCOMING_USER=youruser
    - MAILDEV_INCOMING_PASS=yourpass
    - MAILDEV_SMTP_PORT=1025
    - MAILDEV_WEB_PORT=1080
  ```

---

### **2.2. Networks**

```yaml
networks:
  spring-demo:
    driver: bridge
```

**Explanation:**

1. **Network Name (`spring-demo`):**
   - Defines a custom Docker network named `spring-demo`.
   - **Purpose:** Facilitates communication between services. Containers on the same network can communicate using service names as DNS names.

2. **Driver (`bridge`):**
   - Specifies the network driver. `bridge` is the default driver for Docker networks, suitable for standalone containers.
   - **Other Drivers:**
     - `host`: Shares the host's networking namespace.
     - `overlay`: For multi-host networking.
     - `macvlan`: Assigns a MAC address to containers.
   - **Example Usage:**
     ```yaml
     networks:
       my-network:
         driver: bridge
     ```

**Practical Example: Service Communication**

With both `postgres` and `mail-dev` services connected to the `spring-demo` network, your Spring Boot application (if part of the same network) can communicate with them using their service names.

- **Database Connection URL:**
  - `jdbc:postgresql://postgres-sql-bsn:5432/book_social_network`

- **Mail Server Host:**
  - `localhost` (if the application runs on the host) or `mail-dev-bsn` (if the application runs within another container on the same network).

**Adding Your Application to the Network:**

If you have another service, say `app`, add it to the `spring-demo` network to enable inter-service communication.

```yaml
services:
  app:
    build: .
    ports:
      - "8080:8080"
    networks:
      - spring-demo
    depends_on:
      - postgres
      - mail-dev
```

**Benefits:**

- **Isolation:** Services are isolated from other Docker networks unless explicitly connected.
- **DNS Resolution:** Use service names to refer to containers, simplifying configuration.

---

### **2.3. Volumes**

```yaml
volumes:
  postgres:
    driver: local
```

**Explanation:**

1. **Volume Name (`postgres`):**
   - Defines a Docker-managed volume named `postgres`.
   - **Purpose:** Persists data generated by and used by Docker containers. In this case, it stores PostgreSQL data to ensure data persistence across container restarts or recreations.

2. **Driver (`local`):**
   - Specifies the volume driver. `local` is the default driver, storing data on the host filesystem.
   - **Other Drivers:**
     - `nfs`: Network File System.
     - `aws-ebs`: AWS Elastic Block Store.
     - Custom drivers for specialized storage solutions.
   - **Example Usage:**
     ```yaml
     volumes:
       my-data:
         driver: local
     ```

**Practical Example: Data Persistence**

Without volumes, any data stored inside a container would be lost when the container is removed. Volumes ensure that your PostgreSQL database retains its data even if the container is recreated or updated.

**Steps to Verify Data Persistence:**

1. **Start Services:**
   ```bash
   docker-compose up -d
   ```

2. **Create a Table and Insert Data:**
   ```sql
   CREATE TABLE books (
       id SERIAL PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       author VARCHAR(255) NOT NULL
   );

   INSERT INTO books (title, author) VALUES ('1984', 'George Orwell');
   ```

3. **Stop and Remove Containers:**
   ```bash
   docker-compose down
   ```

4. **Recreate Containers:**
   ```bash
   docker-compose up -d
   ```

5. **Verify Data Persistence:**
   - Connect to PostgreSQL and check if the `books` table and data still exist.

**Advantages of Using Volumes:**

- **Data Persistence:** Ensures critical data isn't lost.
- **Performance:** Volumes are optimized for I/O operations.
- **Isolation:** Data is stored separately from the container lifecycle.

---

#### **Additional Volume Configuration Options:**

- **Bind Mounts:**
  - Mount a specific host directory into the container.
  - **Example:**
    ```yaml
    volumes:
      - ./data/postgres:/var/lib/postgresql/data
    ```
  - **Use Case:** Access and modify database files directly from the host for advanced configurations or backups.

- **Named vs. Anonymous Volumes:**
  - **Named Volumes:** Have a specific name (e.g., `postgres`).
  - **Anonymous Volumes:** Do not have a specified name and are managed by Docker.
  - **Example:**
    ```yaml
    volumes:
      - /var/lib/postgresql/data
    ```

---

#### **Security Considerations:**

- **Access Permissions:**
  - Ensure that the mounted directories or volumes have appropriate permissions to prevent unauthorized access.
  - **Example:**
    ```bash
    chmod 700 ./data/postgres
    chown -R 999:999 ./data/postgres # Assuming PostgreSQL runs as UID 999
    ```

- **Backup Strategies:**
  - Regularly back up volume data to prevent data loss.
  - **Example:**
    ```bash
    docker run --rm -v postgres:/data/postgres -v $(pwd):/backup ubuntu tar cvf /backup/postgres_backup.tar /data/postgres
    ```

---

## **3. Integration with Spring Boot Application**

Your `docker-compose.yml` sets up the necessary infrastructure components—PostgreSQL and MailDev—for your Spring Boot application. Here's how to integrate these services into your Spring Boot project.

### **3.1. Database Configuration**

In your `application.properties` or `application.yml`, configure the datasource to connect to the PostgreSQL service.

**`application.properties`:**
```properties
spring.datasource.url=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

**Explanation:**

- **`jdbc:postgresql://postgres-sql-bsn:5432/book_social_network`:**
  - **Host:** `postgres-sql-bsn` is the container name defined in Docker Compose. Docker's internal DNS resolves this to the PostgreSQL container's IP within the `spring-demo` network.
  - **Port:** `5432` is the default PostgreSQL port, mapped to the host's `5432`.
  - **Database:** `book_social_network` is the database name created via the `POSTGRES_DB` environment variable.

### **3.2. Mail Configuration**

Configure Spring Boot to use MailDev's SMTP server for sending emails during development.

**`application.properties`:**
```properties
spring.mail.host=localhost
spring.mail.port=1025
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth=false
spring.mail.properties.mail.smtp.starttls.enable=false
```

**Explanation:**

- **`spring.mail.host=localhost`:** Points to the SMTP server running on the host machine.
  - **Note:** If your Spring Boot application runs inside another Docker container on the same network, use the service name `mail-dev-bsn` instead of `localhost`.
  - **Example:**
    ```properties
    spring.mail.host=mail-dev-bsn
    ```
- **`spring.mail.port=1025`:** The SMTP port exposed by MailDev.
- **`spring.mail.username` & `spring.mail.password`:** Not required for MailDev.
- **`mail.smtp.auth=false` & `mail.smtp.starttls.enable=false`:** MailDev doesn't require authentication or TLS.

### **3.3. Application Startup Dependencies**

To ensure that your Spring Boot application starts only after PostgreSQL is ready, use the `depends_on` option in Docker Compose.

**Updated `docker-compose.yml`:**
```yaml
services:
  app:
    build: .
    container_name: book-network-app
    image: book-network:latest
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
      SPRING_DATASOURCE_USERNAME: username
      SPRING_DATASOURCE_PASSWORD: password
      SPRING_MAIL_HOST: mail-dev-bsn
      SPRING_MAIL_PORT: 1025
    ports:
      - "8080:8080"
    networks:
      - spring-demo
    depends_on:
      - postgres
      - mail-dev
    restart: unless-stopped
```

**Explanation:**

- **Service `app`:**
  - Represents your Spring Boot application.
  - **Build:** Assumes a `Dockerfile` exists in the project root to build the application image.
  - **Environment Variables:** Overrides Spring Boot properties for datasource and mail configurations.
  - **Ports:** Maps container port `8080` to host port `8080`.
  - **Depends_on:** Ensures `postgres` and `mail-dev` services start before `app`.

**`Dockerfile` Example for Spring Boot Application:**

```dockerfile
# Use official OpenJDK image
FROM openjdk:17-jdk-alpine

# Set environment variables
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    JAVA_OPTS=""

# Set working directory
WORKDIR /app

# Copy the executable JAR
COPY target/book-network-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
```

**Building and Running the Entire Stack:**

1. **Build the Spring Boot Application:**
   ```bash
   mvn clean package
   ```

2. **Build the Docker Image:**
   ```bash
   docker build -t book-network:latest .
   ```

3. **Start Services with Docker Compose:**
   ```bash
   docker-compose up -d
   ```

4. **Access the Application:**
   - **Spring Boot App:** [http://localhost:8080](http://localhost:8080)
   - **MailDev Interface:** [http://localhost:1080](http://localhost:1080)

---

## **4. Running the Docker Compose Setup**

Here's a step-by-step guide to running and managing your Docker Compose setup.

### **4.1. Prerequisites**

- **Docker:** Ensure Docker is installed on your machine. [Install Docker](https://docs.docker.com/get-docker/)
- **Docker Compose:** Typically included with Docker Desktop. Verify installation:
  ```bash
  docker-compose --version
  ```

### **4.2. Starting the Services**

1. **Navigate to the Project Directory:**
   ```bash
   cd path/to/your/project
   ```

2. **Start Services in Detached Mode:**
   ```bash
   docker-compose up -d
   ```

   - **Flags:**
     - `-d`: Runs containers in the background (detached mode).
   - **Output:**
     - Docker pulls the required images if not already present.
     - Containers `postgres-sql-bsn` and `mail-dev-bsn` are started.
     - (If `app` service is defined) Your Spring Boot application container starts and connects to PostgreSQL and MailDev.

3. **Verify Running Containers:**
   ```bash
   docker ps
   ```

   **Sample Output:**
   ```
   CONTAINER ID   IMAGE                      COMMAND                  CREATED          STATUS          PORTS                    NAMES
   abc123def456   postgres:16                "docker-entrypoint.s…"   10 minutes ago   Up 10 minutes   0.0.0.0:5432->5432/tcp   postgres-sql-bsn
   def456ghi789   maildev/maildev            "bin/maildev"            10 minutes ago   Up 10 minutes   0.0.0.0:1080->1080/tcp   mail-dev-bsn
   ```

4. **Check Logs (Optional):**
   ```bash
   docker-compose logs -f
   ```

   - **`-f`:** Follows the logs in real-time.
   - **Use Case:** Monitor application startup, database connections, or troubleshoot issues.

### **4.3. Stopping the Services**

```bash
docker-compose down
```

- **Effect:** Stops and removes containers, networks, and volumes (unless `volumes` are declared as external).

**Additional Flags:**

- **`--volumes` (`-v`):** Removes named volumes declared in the `volumes` section.
  ```bash
  docker-compose down -v
  ```

- **`--rmi` `all`:** Removes all images used by any service.
  ```bash
  docker-compose down --rmi all
  ```

- **`--remove-orphans`:** Removes containers for services not defined in the `docker-compose.yml`.
  ```bash
  docker-compose down --remove-orphans
  ```

### **4.4. Rebuilding Services**

If you make changes to your application code or Dockerfile, rebuild the affected services.

```bash
docker-compose up -d --build
```

- **`--build`:** Forces a rebuild of the images before starting containers.

### **4.5. Managing Services**

- **List All Services:**
  ```bash
  docker-compose ps
  ```

- **View Logs for a Specific Service:**
  ```bash
  docker-compose logs mail-dev
  ```

- **Restart a Service:**
  ```bash
  docker-compose restart postgres
  ```

---

## **5. Advanced Configurations and Best Practices**

To optimize your Docker Compose setup for development and potential production use, consider the following advanced configurations and best practices.

### **5.1. Environment Variables and `.env` Files**

**Using `.env` Files:**

- **Purpose:** Store environment variables separately from the `docker-compose.yml` file for better security and flexibility.
- **Create a `.env` File:**
  ```env
  POSTGRES_USER=username
  POSTGRES_PASSWORD=password
  POSTGRES_DB=book_social_network
  ```
- **Reference in `docker-compose.yml`:**
  ```yaml
  environment:
    POSTGRES_USER: ${POSTGRES_USER}
    POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    POSTGRES_DB: ${POSTGRES_DB}
  ```
- **Benefits:**
  - Keeps sensitive information out of version control.
  - Facilitates different configurations for development, testing, and production.

**Example `.env` File:**
```env
POSTGRES_USER=bookuser
POSTGRES_PASSWORD=securepassword
POSTGRES_DB=book_social_network
MAILDEV_SMTP_PORT=1025
MAILDEV_WEB_PORT=1080
```

### **5.2. Health Checks**

**Implementing Health Checks:**

- **Purpose:** Ensure that services are healthy and ready to accept connections before dependent services start.
- **Example:**
  ```yaml
  services:
    postgres:
      ...
      healthcheck:
        test: ["CMD-SHELL", "pg_isready -U ${POSTGRES_USER}"]
        interval: 10s
        timeout: 5s
        retries: 5

    app:
      ...
      depends_on:
        postgres:
          condition: service_healthy
        mail-dev:
          condition: service_started
  ```
- **Explanation:**
  - **Postgres Health Check:** Uses `pg_isready` to check if PostgreSQL is ready to accept connections.
  - **Service Conditions:**
    - **`service_healthy`:** Waits until the health check passes.
    - **`service_started`:** Waits until the service starts (no health check).

**Note:** In Docker Compose version 3.4 and above, `depends_on` conditions are deprecated in favor of external health check tools or orchestration systems.

### **5.3. Using Docker Networks for Multiple Environments**

**Segregate Environments:**

- **Purpose:** Isolate different environments (e.g., development, testing, production) using separate Docker networks.
- **Example:**
  ```yaml
  networks:
    dev-network:
      driver: bridge
    prod-network:
      driver: bridge

  services:
    postgres:
      networks:
        - dev-network
    app:
      networks:
        - dev-network
  ```

**Benefits:**

- Prevents services from different environments from interfering with each other.
- Enhances security by restricting access within networks.

### **5.4. Volume Backups and Restoration**

**Backing Up Volumes:**

- **Command:**
  ```bash
  docker run --rm -v postgres:/data/postgres -v $(pwd):/backup ubuntu tar cvf /backup/postgres_backup.tar /data/postgres
  ```
- **Explanation:**
  - **Mount Volumes:**
    - **`-v postgres:/data/postgres`:** Mounts the `postgres` volume.
    - **`-v $(pwd):/backup`:** Mounts the current directory to `/backup` inside the container.
  - **Command:** Uses `tar` to archive the `/data/postgres` directory into `postgres_backup.tar` in the host's current directory.

**Restoring Volumes:**

- **Command:**
  ```bash
  docker run --rm -v postgres:/data/postgres -v $(pwd):/backup ubuntu bash -c "cd /data/postgres && tar xvf /backup/postgres_backup.tar --strip 1"
  ```

**Note:** Ensure that PostgreSQL is stopped or the volume is not in use during restoration to prevent data corruption.

### **5.5. Securing Services**

**Restricting Access to Services:**

- **Bind Services to Internal Networks:**
  - **Purpose:** Prevent external access to services that shouldn't be exposed (e.g., databases).
  - **Example:**
    ```yaml
    services:
      postgres:
        ports:
          - "5432:5432" # To allow host access
        # To restrict, remove or limit port mapping
    ```
    - **Internal Only:**
      ```yaml
      services:
        postgres:
          networks:
            - spring-demo
          # No ports mapping
      ```
    - **Access via Docker Network Only:**
      - The service is accessible only to other services on the same network, not from the host.

**Using Environment Variables for Sensitive Data:**

- **Avoid Hardcoding:**
  - Use environment variables or Docker secrets to manage sensitive information like passwords.

**Example: Using Secrets (Docker Swarm):**

1. **Create a Secret:**
   ```bash
   echo "securepassword" | docker secret create postgres_password -
   ```

2. **Reference in `docker-compose.yml`:**
   ```yaml
   services:
     postgres:
       image: postgres:16
       secrets:
         - postgres_password
       environment:
         POSTGRES_PASSWORD_FILE: /run/secrets/postgres_password
   secrets:
     postgres_password:
       external: true
   ```

**Note:** Docker secrets are more suited for Docker Swarm and not standalone Docker Compose setups.

### **5.6. Optimizing Dockerfile for Spring Boot Application**

**Example Optimized `Dockerfile`:**

```dockerfile
# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/book-network-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Explanation:**

- **Multi-Stage Build:**
  - **Stage 1 (Build):**
    - Uses Maven Docker image to build the application.
    - Copies `pom.xml` and `src` directory.
    - Runs `mvn clean package` to compile and package the application.
  - **Stage 2 (Run):**
    - Uses a lightweight OpenJDK image.
    - Copies the packaged JAR from the build stage.
    - Exposes port `8080`.
    - Sets the entry point to run the application.

**Benefits:**

- **Smaller Final Image:** Only the necessary JAR is included, reducing image size.
- **Separation of Concerns:** Build and runtime environments are isolated.
- **Security:** Fewer components in the runtime image reduce attack surfaces.

### **5.7. Health Checks for MailDev Service**

**Adding Health Checks:**

```yaml
services:
  mail-dev:
    container_name: mail-dev-bsn
    image: maildev/maildev
    ports:
      - "1080:1080"
      - "1025:1025"
    networks:
      - spring-demo
    restart: unless-stopped
    healthcheck:
      test: ["CMD", "curl", "-f", "http://localhost:1080"]
      interval: 30s
      timeout: 10s
      retries: 5
```

**Explanation:**

- **Health Check Command:**
  - Uses `curl` to make a request to the MailDev web interface.
  - **`-f`:** Fails silently on server errors (non-2xx responses).

- **Intervals:**
  - **`interval`:** Time between health checks (30 seconds).
  - **`timeout`:** Maximum time to wait for a response (10 seconds).
  - **`retries`:** Number of consecutive failures before marking the service as unhealthy.

**Use Case:**

Ensures that MailDev is up and running before dependent services (if any) start interacting with it.

---

### **5.8. Scaling Services**

**Scaling with Docker Compose:**

- **Command:**
  ```bash
  docker-compose up -d --scale app=3
  ```

- **Explanation:**
  - **`--scale app=3`:** Runs three instances of the `app` service.
  
- **Considerations:**
  - Ensure that your application can handle multiple instances, especially concerning shared resources like databases.
  - Load balancing might be necessary if scaling web services.

**Example: Scaling a Web Service**

```yaml
services:
  app:
    build: .
    ports:
      - "8080:8080"
    networks:
      - spring-demo
    depends_on:
      - postgres
      - mail-dev
    restart: unless-stopped
```

- **Scaling:**
  ```bash
  docker-compose up -d --scale app=3
  ```

- **Result:**
  - Three instances of `app` are running, accessible via port `8080` on the host. To avoid port conflicts, consider using a reverse proxy or load balancer like Nginx.

---

### **5.9. Using a Reverse Proxy (e.g., Nginx) for Load Balancing**

**Adding Nginx to `docker-compose.yml`:**

```yaml
services:
  nginx:
    image: nginx:latest
    container_name: nginx-bsn
    ports:
      - "80:80"
    volumes:
      - ./nginx.conf:/etc/nginx/nginx.conf
    networks:
      - spring-demo
    depends_on:
      - app
    restart: unless-stopped

  app:
    build: .
    container_name: book-network-app
    image: book-network:latest
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
      SPRING_DATASOURCE_USERNAME: username
      SPRING_DATASOURCE_PASSWORD: password
      SPRING_MAIL_HOST: mail-dev-bsn
      SPRING_MAIL_PORT: 1025
    ports:
      - "8080:8080"
    networks:
      - spring-demo
    depends_on:
      - postgres
      - mail-dev
    restart: unless-stopped
    deploy:
      replicas: 3
```

**`nginx.conf` Example:**

```nginx
events { }

http {
    upstream app_servers {
        server book-network-app:8080;
        server book-network-app:8080;
        server book-network-app:8080;
    }

    server {
        listen 80;

        location / {
            proxy_pass http://app_servers;
            proxy_set_header Host $host;
            proxy_set_header X-Real-IP $remote_addr;
        }
    }
}
```

**Explanation:**

- **Nginx Service:**
  - Acts as a reverse proxy and load balancer for the `app` service.
  - **Volumes:**
    - Mounts a custom `nginx.conf` to configure Nginx.
  - **Ports:**
    - Maps host port `80` to container port `80`.

- **App Service:**
  - **Replicas:**
    - Defines three instances (`replicas: 3`) of the application.

- **Nginx Configuration:**
  - **`upstream app_servers`:**
    - Lists multiple `app` service instances. However, note that in Docker Compose, service names are unique. For multiple replicas, Docker assigns unique container names with suffixes.
  - **Dynamic Discovery:**
    - To correctly reference multiple instances, use Docker's DNS and service discovery features or use a different naming strategy.
    - **Example Update:**
      ```nginx
      upstream app_servers {
          server app:8080;
      }
      ```
      - Docker's internal load balancing ensures traffic is distributed across replicas.

**Note:** Managing multiple replicas and service discovery can be more seamlessly handled using orchestration tools like Kubernetes. Docker Compose is primarily suited for development and simple deployments.

---

### **5.10. Environment-Specific Configurations**

**Using Multiple Compose Files:**

- **Purpose:** Define environment-specific settings (e.g., development, production) by extending the base `docker-compose.yml` with additional configurations.
  
- **Example:**
  - **`docker-compose.prod.yml`:**
    ```yaml
    services:
      app:
        environment:
          SPRING_PROFILES_ACTIVE: prod
        deploy:
          replicas: 5
          resources:
            limits:
              cpus: "0.5"
              memory: "512M"
    ```
  
- **Running with Multiple Files:**
  ```bash
  docker-compose -f docker-compose.yml -f docker-compose.prod.yml up -d
  ```

**Benefits:**

- **Flexibility:** Customize services for different environments without duplicating configurations.
- **Maintainability:** Keep base configurations separate from environment-specific overrides.

---

### **5.11. Monitoring and Logging**

**Centralized Logging:**

- **Use Docker Logging Drivers:** Forward logs to centralized systems like ELK Stack, Fluentd, or Splunk.
- **Example: Forwarding to a File:**
  ```yaml
  services:
    app:
      logging:
        driver: "json-file"
        options:
          max-size: "10m"
          max-file: "3"
  ```

**Monitoring Containers:**

- **Tools:**
  - **Portainer:** Web-based Docker management UI.
  - **Prometheus & Grafana:** For metrics collection and visualization.
  - **cAdvisor:** Collects container metrics.

**Example: Adding cAdvisor for Monitoring:**
```yaml
services:
  cadvisor:
    image: google/cadvisor:latest
    container_name: cadvisor
    ports:
      - "8081:8080"
    volumes:
      - /:/rootfs:ro
      - /var/run:/var/run:rw
      - /sys:/sys:ro
      - /var/lib/docker/:/var/lib/docker:ro
    networks:
      - spring-demo
    restart: unless-stopped
```

**Accessing Metrics:**
- Navigate to [http://localhost:8081](http://localhost:8081) to view container metrics.

---

### **5.12. Handling Data Migrations**

**Using Flyway or Liquibase:**

- **Purpose:** Automate database schema migrations.
- **Integration with Spring Boot:**
  - **Flyway:** Integrates seamlessly with Spring Boot.
  - **Example Configuration:**
    ```properties
    spring.flyway.enabled=true
    spring.flyway.locations=classpath:db/migration
    ```
  - **Migration Scripts:**
    - Place SQL scripts in `src/main/resources/db/migration`.
    - **Example:** `V1__Create_users_table.sql`
      ```sql
      CREATE TABLE users (
          id SERIAL PRIMARY KEY,
          username VARCHAR(50) NOT NULL UNIQUE,
          email VARCHAR(100) NOT NULL UNIQUE,
          password VARCHAR(255) NOT NULL
      );
      ```

**Benefits:**

- **Version Control:** Track and manage database changes over time.
- **Automation:** Automatically apply migrations during application startup.

---

### **5.13. Using Docker Secrets for Sensitive Data (Advanced)**

**Note:** Docker Secrets are more suited for Docker Swarm and not standard Docker Compose. For standalone Compose, environment variables or external secret management tools are recommended.

**Example with Docker Swarm:**

1. **Initialize Swarm:**
   ```bash
   docker swarm init
   ```

2. **Create a Secret:**
   ```bash
   echo "securepassword" | docker secret create postgres_password -
   ```

3. **Reference the Secret in `docker-compose.yml`:**
   ```yaml
   services:
     postgres:
       image: postgres:16
       secrets:
         - postgres_password
       environment:
         POSTGRES_PASSWORD_FILE: /run/secrets/postgres_password
       volumes:
         - postgres:/var/lib/postgresql/data
       networks:
         - spring-demo
       restart: unless-stopped
       deploy:
         replicas: 1

   secrets:
     postgres_password:
       external: true
   ```

**Explanation:**

- **Secrets:**
  - Stored securely by Docker.
  - Mounted as files inside containers, accessed via file paths.

- **Environment Variable:**
  - **`POSTGRES_PASSWORD_FILE`:** Points PostgreSQL to read the password from the mounted secret file.

---

### **5.14. Optimizing Docker Images for Faster Builds**

**Caching Dependencies:**

- **Leverage Docker Layer Caching:**
  - Structure your `Dockerfile` to cache dependencies separately from application code.
  - **Example:**
    ```dockerfile
    # Stage 1: Build
    FROM maven:3.8.4-openjdk-17 AS build
    WORKDIR /app
    COPY pom.xml .
    RUN mvn dependency:go-offline
    COPY src ./src
    RUN mvn clean package -DskipTests

    # Stage 2: Run
    FROM openjdk:17-jdk-alpine
    WORKDIR /app
    COPY --from=build /app/target/book-network-0.0.1-SNAPSHOT.jar app.jar
    EXPOSE 8080
    ENTRYPOINT ["java", "-jar", "app.jar"]
    ```
  - **Explanation:**
    - **`RUN mvn dependency:go-offline`:** Downloads all dependencies in advance.
    - **Cache Benefit:** If only application code changes, Docker can reuse the cached dependencies layer, speeding up builds.

**Minimizing Image Size:**

- **Use Multi-Stage Builds:** As shown above, separate build and runtime environments.
- **Choose Minimal Base Images:** Use lightweight images like `alpine` variants.
- **Remove Unnecessary Files:** Exclude development tools or temporary files from the final image.

---

### **5.15. Handling Service Dependencies**

**Ensuring Services Start in Order:**

- **`depends_on`:** Ensures that a service starts after its dependencies. However, it doesn't wait for the dependencies to be "ready".
- **Solution:** Use health checks to wait for dependencies to be healthy before starting dependent services.

**Example: Using Wait-for-it Script:**

1. **Add `wait-for-it.sh` to Your Project:**
   - [wait-for-it](https://github.com/vishnubob/wait-for-it)

2. **Modify Dockerfile:**
   ```dockerfile
   COPY wait-for-it.sh /wait-for-it.sh
   RUN chmod +x /wait-for-it.sh
   ```

3. **Update `docker-compose.yml`:**
   ```yaml
   services:
     app:
       ...
       entrypoint: ["./wait-for-it.sh", "postgres-sql-bsn:5432", "--", "java", "-jar", "app.jar"]
   ```

**Explanation:**

- **`wait-for-it.sh`:** Waits for a service (e.g., PostgreSQL) to be ready before executing the application.

---

### **5.16. Scaling and Load Balancing**

For production-grade deployments, consider integrating load balancers and scaling services appropriately. Docker Compose is limited in this aspect, so orchestration tools like **Kubernetes** or **Docker Swarm** are recommended for advanced scaling and load balancing.

**Example: Using Kubernetes with Docker Compose:**

- **Convert `docker-compose.yml` to Kubernetes Manifests:**
  - Use tools like **Kompose** to automate the conversion.
    ```bash
    kompose convert
    ```
  
- **Deploy to Kubernetes Cluster:**
  ```bash
  kubectl apply -f postgres-deployment.yaml
  kubectl apply -f maildev-deployment.yaml
  kubectl apply -f app-deployment.yaml
  kubectl apply -f nginx-deployment.yaml
    ```

---

## **3. Integration with Spring Boot Application**

Your Docker Compose setup includes services that your Spring Boot application will interact with: PostgreSQL for data storage and MailDev for email handling during development. Here's how to integrate these services seamlessly.

### **3.1. Configuring Spring Boot to Connect to PostgreSQL**

Ensure your Spring Boot application is configured to connect to the PostgreSQL database defined in your `docker-compose.yml`.

**`application.properties`:**
```properties
spring.datasource.url=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

**Explanation:**

- **`jdbc:postgresql://postgres-sql-bsn:5432/book_social_network`:**
  - **Host:** `postgres-sql-bsn` is the container name specified in Docker Compose, acting as the hostname within the `spring-demo` network.
  - **Port:** `5432` is the PostgreSQL port mapped to the host.
  - **Database:** `book_social_network` is the database created via the `POSTGRES_DB` environment variable.

**Spring Data JPA Configuration:**

- **`spring.jpa.hibernate.ddl-auto=update`:**
  - Automatically updates the database schema based on your JPA entities.
  - **Note:** For production environments, consider using `validate` or `none` to prevent unintended schema changes.

### **3.2. Configuring Spring Boot to Use MailDev for Email Testing**

Configure your application to send emails via MailDev's SMTP server during development.

**`application.properties`:**
```properties
spring.mail.host=localhost
spring.mail.port=1025
spring.mail.username=
spring.mail.password=
spring.mail.properties.mail.smtp.auth=false
spring.mail.properties.mail.smtp.starttls.enable=false
```

**Explanation:**

- **`spring.mail.host=localhost`:** Points to the SMTP server running on the host machine. If your Spring Boot app runs inside another container on the same network, use `mail-dev-bsn`.
  - **Example for Containerized App:**
    ```properties
    spring.mail.host=mail-dev-bsn
    ```
- **`spring.mail.port=1025`:** SMTP port exposed by MailDev.
- **`spring.mail.username` & `spring.mail.password`:** Not required for MailDev.
- **`mail.smtp.auth=false` & `mail.smtp.starttls.enable=false`:** MailDev doesn't require authentication or TLS.

**Sending Emails in Spring Boot:**

**Email Service Example:**
```java
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendWelcomeEmail(String to, String username) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Welcome to Book Network!");
        message.setText("Hello " + username + ", welcome to our platform.");
        mailSender.send(message);
    }
}
```

**Controller Usage:**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        // Registration logic
        emailService.sendWelcomeEmail(userDto.getEmail(), userDto.getUsername());
        return ResponseEntity.ok("User registered successfully");
    }
}
```

**Viewing Sent Emails:**

- Navigate to [http://localhost:1080](http://localhost:1080) to access the MailDev web interface and view sent emails.

**Advantages:**

- **No Real Emails Sent:** Prevents accidental spamming during development.
- **Immediate Feedback:** View and debug emails directly via the MailDev UI.

---

## **4. Running the Docker Compose Setup**

To effectively utilize your `docker-compose.yml`, follow these steps to build, run, and manage your Dockerized environment.

### **4.1. Prerequisites**

- **Docker Installed:** Ensure Docker is installed on your machine. [Download Docker](https://www.docker.com/get-started)
- **Docker Compose Installed:** Typically included with Docker Desktop. Verify installation:
  ```bash
  docker-compose --version
  ```

### **4.2. Building and Running Services**

1. **Navigate to Your Project Directory:**
   ```bash
   cd path/to/your/project
   ```

2. **Start Services in Detached Mode:**
   ```bash
   docker-compose up -d
   ```

   - **`-d`:** Runs containers in the background (detached mode).

3. **Verify Running Containers:**
   ```bash
   docker-compose ps
   ```

   **Sample Output:**
   ```
   Name                  Command                  State           Ports
   -----------------------------------------------------------------------------------
   mail-dev-bsn          "bin/maildev"            Up      0.0.0.0:1080->1080/tcp, 0.0.0.0:1025->1025/tcp
   postgres-sql-bsn     "docker-entrypoint.s…"   Up      0.0.0.0:5432->5432/tcp
   ```

4. **View Logs (Optional):**
   ```bash
   docker-compose logs -f
   ```

   - **`-f`:** Follows the logs in real-time.
   - **Use Case:** Monitor service startups, application logs, or troubleshoot issues.

### **4.3. Stopping and Removing Services**

1. **Stop Services:**
   ```bash
   docker-compose stop
   ```

2. **Remove Containers, Networks, and Volumes:**
   ```bash
   docker-compose down
   ```

   - **Flags:**
     - `-v`: Removes named volumes declared in the `volumes` section.
     - `--rmi all`: Removes all images used by any service.
     - `--remove-orphans`: Removes containers for services not defined in the `docker-compose.yml`.

   **Example:**
   ```bash
   docker-compose down -v --rmi all --remove-orphans
   ```

### **4.4. Rebuilding Services**

If you make changes to your application code or Dockerfile, rebuild the affected services.

```bash
docker-compose up -d --build
```

- **`--build`:** Forces a rebuild of the images before starting containers.

### **4.5. Scaling Services**

**Example: Scaling the Application Service**

If your `docker-compose.yml` includes an `app` service (Spring Boot application), you can scale it.

```bash
docker-compose up -d --scale app=3
```

**Explanation:**

- **`--scale app=3`:** Runs three instances of the `app` service.
- **Note:** Ensure that your application can handle multiple instances, especially regarding shared resources like databases.

**Accessing Scaled Services:**

- **Load Balancing:** Use a reverse proxy (e.g., Nginx) to distribute traffic across multiple `app` instances.
- **Service Discovery:** Utilize Docker's internal DNS to reference services by name.

---

## **5. Advanced Configurations and Best Practices**

To enhance the robustness, security, and maintainability of your Docker Compose setup, consider implementing the following advanced configurations and best practices.

### **5.1. Using `.env` Files for Environment Variables**

**Purpose:** Store environment-specific variables outside the `docker-compose.yml` to keep configurations flexible and secure.

**Steps:**

1. **Create a `.env` File in the Project Root:**
   ```env
   POSTGRES_USER=username
   POSTGRES_PASSWORD=password
   POSTGRES_DB=book_social_network
   MAILDEV_SMTP_PORT=1025
   MAILDEV_WEB_PORT=1080
   ```

2. **Reference Variables in `docker-compose.yml`:**
   ```yaml
   environment:
     POSTGRES_USER: ${POSTGRES_USER}
     POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
     POSTGRES_DB: ${POSTGRES_DB}
   ```

3. **Security Consideration:**
   - **Add `.env` to `.gitignore`:**
     ```gitignore
     # .gitignore
     .env
     ```
   - **Purpose:** Prevents sensitive information from being committed to version control.

**Advantages:**

- **Flexibility:** Easily switch configurations for different environments (development, testing, production).
- **Security:** Keeps sensitive data out of the source code.

---

### **5.2. Implementing Health Checks**

**Purpose:** Ensure that services are up and running correctly before dependent services start.

**Example: Health Check for PostgreSQL**

```yaml
services:
  postgres:
    ...
    healthcheck:
      test: ["CMD", "pg_isready", "-U", "username"]
      interval: 10s
      timeout: 5s
      retries: 5
```

**Explanation:**

- **`pg_isready`:** Utility to check PostgreSQL server readiness.
- **`interval`:** Time between each health check attempt.
- **`timeout`:** Maximum time to wait for a response.
- **`retries`:** Number of consecutive failures before marking the service as unhealthy.

**Dependent Service Waits:**

While `depends_on` ensures container startup order, it doesn't wait for health checks to pass. For more granular control, use scripts like `wait-for-it` or orchestration tools that support health-based dependencies.

---

### **5.3. Securing Sensitive Information**

**Avoid Hardcoding Secrets:**

- **Use Environment Variables:** As shown with `.env` files.
- **Docker Secrets:** Suitable for Docker Swarm environments.

**Example: Using Docker Secrets (Swarm Mode):**

1. **Create a Secret:**
   ```bash
   echo "supersecretpassword" | docker secret create postgres_password -
   ```

2. **Reference in `docker-compose.yml`:**
   ```yaml
   services:
     postgres:
       ...
       secrets:
         - postgres_password
       environment:
         POSTGRES_PASSWORD_FILE: /run/secrets/postgres_password
   secrets:
     postgres_password:
       external: true
   ```

**Explanation:**

- **`postgres_password`:** Secret stored securely by Docker.
- **`POSTGRES_PASSWORD_FILE`:** Environment variable pointing to the secret file inside the container.

**Note:** Docker secrets are primarily designed for Docker Swarm and not for standalone Compose.

---

### **5.4. Implementing Logging and Monitoring**

**Centralized Logging:**

- **Use Logging Drivers:**
  - **Example:** Forward logs to a centralized logging system like ELK Stack or Fluentd.
    ```yaml
    services:
      app:
        ...
        logging:
          driver: "json-file"
          options:
            max-size: "10m"
            max-file: "3"
    ```

**Monitoring Services:**

- **Use Tools like cAdvisor or Prometheus:**
  - **Example: Adding cAdvisor to Monitor Containers:**
    ```yaml
    services:
      cadvisor:
        image: google/cadvisor:latest
        container_name: cadvisor-bsn
        ports:
          - "8081:8080"
        volumes:
          - /:/rootfs:ro
          - /var/run:/var/run:rw
          - /sys:/sys:ro
          - /var/lib/docker/:/var/lib/docker:ro
        networks:
          - spring-demo
        restart: unless-stopped
    ```
  - **Access Metrics:** Navigate to [http://localhost:8081](http://localhost:8081)

**Advantages:**

- **Proactive Monitoring:** Detect and respond to issues promptly.
- **Centralized Logs:** Simplifies debugging and auditing.

---

### **5.5. Automating Backups**

**Database Backups:**

1. **Create a Backup Script:**
   ```bash
   # backup.sh
   #!/bin/bash
   docker exec postgres-sql-bsn pg_dump -U username book_social_network > backup.sql
   echo "Backup completed successfully."
   ```

2. **Make the Script Executable:**
   ```bash
   chmod +x backup.sh
   ```

3. **Run the Backup:**
   ```bash
   ./backup.sh
   ```

**Explanation:**

- **`pg_dump`:** Utility to back up PostgreSQL databases.
- **Output:** `backup.sql` contains SQL commands to recreate the database.

**Automate with Cron Jobs:**

- **Example: Daily Backups at Midnight**
  ```bash
  crontab -e
  ```
  Add the following line:
  ```cron
  0 0 * * * /path/to/backup.sh
  ```

**Advantages:**

- **Data Safety:** Protects against data loss.
- **Recovery:** Enables restoring the database to a previous state.

---

### **5.6. Optimizing Docker Images**

**Use Multi-Stage Builds:**

- **Purpose:** Reduce final image size by separating build and runtime environments.
- **Example `Dockerfile`:**
  ```dockerfile
  # Stage 1: Build
  FROM maven:3.8.4-openjdk-17 AS build
  WORKDIR /app
  COPY pom.xml .
  RUN mvn dependency:go-offline
  COPY src ./src
  RUN mvn clean package -DskipTests

  # Stage 2: Run
  FROM openjdk:17-jdk-alpine
  WORKDIR /app
  COPY --from=build /app/target/book-network-0.0.1-SNAPSHOT.jar app.jar
  EXPOSE 8080
  ENTRYPOINT ["java", "-jar", "app.jar"]
  ```

**Explanation:**

- **Stage 1 (Build):**
  - Uses a Maven image to compile and package the application.
  - **`mvn dependency:go-offline`:** Downloads all dependencies, leveraging Docker's caching to speed up subsequent builds.
  
- **Stage 2 (Run):**
  - Uses a lightweight OpenJDK Alpine image.
  - Copies the packaged JAR from the build stage.
  - **`ENTRYPOINT`:** Defines the command to run the application.

**Benefits:**

- **Smaller Image Size:** Excludes build tools and source code from the final image.
- **Security:** Reduces the attack surface by minimizing installed packages.
- **Faster Deployment:** Smaller images are quicker to transfer and start.

---

### **5.7. Using Docker Compose Overrides for Development**

**Purpose:** Customize configurations for different environments without altering the base `docker-compose.yml`.

**Example: `docker-compose.override.yml`:**

```yaml
services:
  postgres:
    environment:
      POSTGRES_PASSWORD: devpassword
    volumes:
      - ./data/postgres:/var/lib/postgresql/data
    ports:
      - "5432:5432"

  mail-dev:
    ports:
      - "1080:1080"
      - "1025:1025"

  app:
    environment:
      SPRING_PROFILES_ACTIVE: dev
    ports:
      - "8080:8080"
    volumes:
      - ./logs:/app/logs
```

**Explanation:**

- **`docker-compose.override.yml`:**
  - Automatically applied when running `docker-compose up`.
  - **Customizations:**
    - **PostgreSQL:**
      - Overrides `POSTGRES_PASSWORD` for development.
      - Mounts a host directory for data persistence.
    - **App:**
      - Activates the `dev` profile.
      - Mounts a host directory for log files.

**Usage:**

- **Run with Overrides:**
  ```bash
  docker-compose up -d
  ```
  - Docker Compose automatically merges `docker-compose.yml` and `docker-compose.override.yml`.

**Benefits:**

- **Separation of Concerns:** Keeps base configurations clean and environment-specific customizations separate.
- **Flexibility:** Easily switch configurations by using different override files.

---

### **5.8. Leveraging Docker Compose Commands**

**Common Commands:**

- **Start Services:**
  ```bash
  docker-compose up -d
  ```
- **Stop Services:**
  ```bash
  docker-compose down
  ```
- **Rebuild Services:**
  ```bash
  docker-compose up -d --build
  ```
- **View Logs:**
  ```bash
  docker-compose logs -f
  ```
- **Scale Services:**
  ```bash
  docker-compose up -d --scale app=3
  ```
- **List Services:**
  ```bash
  docker-compose ps
  ```

**Tips:**

- **Use `-d` for Detached Mode:** Prevents logs from cluttering the terminal.
- **Combine Commands for Efficiency:**
  - **Example:** Rebuild and start services in one command.
    ```bash
    docker-compose up -d --build
    ```

---

### **5.9. Managing Data with Docker Volumes**

**Listing Docker Volumes:**
```bash
docker volume ls
```

**Inspecting a Volume:**
```bash
docker volume inspect postgres
```

**Removing Unused Volumes:**
```bash
docker volume prune
```

**Note:** Be cautious when removing volumes as it deletes all data stored within them.

---

### **5.10. Testing the Setup**

**Verify PostgreSQL Connection:**

1. **Connect via psql:**
   ```bash
   docker exec -it postgres-sql-bsn psql -U username -d book_social_network
   ```
2. **Run SQL Commands:**
   ```sql
   SELECT NOW();
   ```

**Verify MailDev Functionality:**

1. **Send a Test Email from Application:**
   - Trigger an email sending action in your application.
2. **Check MailDev UI:**
   - Navigate to [http://localhost:1080](http://localhost:1080) to view the sent email.

---

### **5.11. Optimizing Docker Compose for Production**

While the provided `docker-compose.yml` is suitable for development, production environments often require additional considerations:

- **Use Docker Swarm or Kubernetes:** For orchestration, scaling, and advanced networking.
- **Secure Network Configurations:** Limit network access to essential services only.
- **Implement TLS:** Secure communication between services and external clients.
- **Resource Limits:** Define CPU and memory limits to prevent resource exhaustion.
  ```yaml
  services:
    app:
      deploy:
        resources:
          limits:
            cpus: "1.0"
            memory: "512M"
  ```
- **Monitoring and Alerting:** Integrate with monitoring tools to track application health and performance.

---

### **5.12. Using External Services and Integrations**

**Example: Integrating Redis for Caching**

1. **Add Redis Service:**
   ```yaml
   services:
     redis:
       image: redis:7
       container_name: redis-bsn
       ports:
         - "6379:6379"
       networks:
         - spring-demo
       restart: unless-stopped
   ```

2. **Configure Spring Boot to Use Redis:**
   **`application.properties`:**
   ```properties
   spring.cache.type=redis
   spring.redis.host=redis-bsn
   spring.redis.port=6379
   ```

3. **Enable Caching in Spring Boot:**
   ```java
   @EnableCaching
   @SpringBootApplication
   public class BookNetworkApplication {
       public static void main(String[] args) {
           SpringApplication.run(BookNetworkApplication.class, args);
       }
   }
   ```

**Advantages:**

- **Performance:** Caching improves application responsiveness.
- **Scalability:** Redis can handle high-throughput scenarios.

---

## **6. Conclusion**

Your `docker-compose.yml` serves as the backbone for managing essential services required by your **Book Social Network** application. By leveraging Docker Compose, you've streamlined the setup process, ensuring that services like PostgreSQL and MailDev are consistently configured and easily manageable. Here's a recap of what we've covered:

- **Services:** Defined PostgreSQL and MailDev with configurations for environment variables, volumes, ports, networks, and restart policies.
- **Networks:** Created a bridge network (`spring-demo`) to facilitate communication between services.
- **Volumes:** Established persistent storage for PostgreSQL data to ensure data durability.
- **Integration:** Illustrated how to configure your Spring Boot application to interact with these services.
- **Advanced Configurations:** Explored best practices for environment variables, health checks, security, logging, backups, and more.

**Next Steps:**

1. **Implement Continuous Integration/Continuous Deployment (CI/CD):** Automate your build, test, and deployment processes using tools like GitHub Actions, Jenkins, or GitLab CI.

2. **Enhance Security:** Secure sensitive data, enforce network policies, and implement TLS for encrypted communications.

3. **Optimize Performance:** Monitor application performance, scale services as needed, and implement caching strategies.

4. **Expand Infrastructure:** Integrate additional services (e.g., Redis, Elasticsearch) to enhance application features and capabilities.

5. **Documentation:** Maintain comprehensive documentation for your Docker setup to facilitate onboarding and collaboration.

By adhering to these practices and continuously refining your Docker Compose configurations, you'll ensure a robust, scalable, and maintainable environment for your social media application focused on book sharing.

---

**Additional Resources:**

- [Docker Compose Official Documentation](https://docs.docker.com/compose/)
- [Spring Boot and Docker Guide](https://spring.io/guides/topicals/spring-boot-docker/)
- [MailDev GitHub Repository](https://github.com/maildev/maildev)
- [PostgreSQL Docker Image Documentation](https://hub.docker.com/_/postgres)

Feel free to reach out if you have further questions or need assistance with specific configurations!

# 📗application.yml files

application.yml

```yml
spring:
  profiles:
    active: dev
  servlet:
    multipart:
      max-file-size: 50MB
springdoc:
  default-produces-media-type: application/json
server:
  servlet:
    context-path: /api/v1/
```

application-dev.yml
```yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/book_social_network
    username: username
    password: password
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
  mail:
    host: localhost
    port: 1025
    username: username
    password: password
    properties:
      mail:
        smtp:
          trust: "*"
        auth: true
        starttls:
          enable: true
        connectiontimeout: 5000
        timeout: 3000
        writetimeout: 5000
```

Certainly! Let's delve deeply into your **Spring Boot** application's configuration files: `application.yml` and `application-dev.yml`. We'll explore each configuration property, understand their roles, and see how they integrate with your **Docker Compose** setup for a seamless development environment.

---

## **Table of Contents**

1. [Overview of Spring Boot Configuration](#1-overview-of-spring-boot-configuration)
2. [Understanding `application.yml`](#2-understanding-applicationyml)
   - [2.1. Spring Profiles](#21-spring-profiles)
   - [2.2. Servlet Multipart Configuration](#22-servlet-multipart-configuration)
   - [2.3. SpringDoc OpenAPI Configuration](#23-springdoc-openapi-configuration)
   - [2.4. Server Configuration](#24-server-configuration)
3. [Understanding `application-dev.yml`](#3-understanding-application-devyml)
   - [3.1. DataSource Configuration](#31-datasource-configuration)
   - [3.2. JPA (Hibernate) Configuration](#32-jpa-hibernate-configuration)
   - [3.3. Mail Configuration](#33-mail-configuration)
4. [Integration with Docker Compose](#4-integration-with-docker-compose)
5. [Practical Examples and Usage](#5-practical-examples-and-usage)
   - [5.1. Switching Between Profiles](#51-switching-between-profiles)
   - [5.2. Handling File Uploads](#52-handling-file-uploads)
   - [5.3. Accessing API Documentation](#53-accessing-api-documentation)
   - [5.4. Sending Emails During Development](#54-sending-emails-during-development)
6. [Best Practices and Recommendations](#6-best-practices-and-recommendations)
   - [6.1. Securely Managing Secrets](#61-securely-managing-secrets)
   - [6.2. Externalizing Configuration](#62-externalizing-configuration)
   - [6.3. Optimizing Performance](#63-optimizing-performance)
   - [6.4. Comprehensive Logging](#64-comprehensive-logging)
7. [Conclusion](#7-conclusion)
8. [Additional Resources](#8-additional-resources)

---

## **1. Overview of Spring Boot Configuration**

Spring Boot simplifies application configuration through **externalized configuration**. This approach allows you to define configuration properties outside your code, making your application more flexible and easier to manage across different environments (e.g., development, testing, production).

- **`application.yml`**: The primary configuration file containing common settings and specifying active profiles.
- **`application-{profile}.yml`**: Profile-specific configuration files that override or extend properties defined in `application.yml` based on the active profile.

Your setup includes:
- **`application.yml`**: Defines general settings and activates the `dev` profile.
- **`application-dev.yml`**: Contains configurations specific to the development environment, such as database and mail server settings.

---

## **2. Understanding `application.yml`**

Let's break down each section of your `application.yml` to understand its purpose and configuration.

```yaml
spring:
  profiles:
    active: dev
  servlet:
    multipart:
      max-file-size: 50MB
springdoc:
  default-produces-media-type: application/json
server:
  servlet:
    context-path: /api/v1/
```

### **2.1. Spring Profiles**

```yaml
spring:
  profiles:
    active: dev
```

- **Purpose**: Specifies the active profile(s) for the application.
- **Explanation**:
  - **`spring.profiles.active`**: Determines which profile-specific configuration file to load. Here, it's set to `dev`, so `application-dev.yml` will be loaded alongside `application.yml`.
  - **Profiles**: Allow you to define environment-specific configurations without changing your codebase. Common profiles include `dev`, `test`, `prod`.

- **Multiple Profiles**:
  - You can activate multiple profiles by separating them with commas:
    ```yaml
    spring:
      profiles:
        active: dev,featureX
    ```

- **Example Usage**:
  - **Development (`dev`)**: Uses local resources, debug settings.
  - **Production (`prod`)**: Uses optimized settings, production databases.

### **2.2. Servlet Multipart Configuration**

```yaml
spring:
  servlet:
    multipart:
      max-file-size: 50MB
```

- **Purpose**: Configures the handling of multipart (file upload) requests.
- **Explanation**:
  - **`spring.servlet.multipart.max-file-size`**: Sets the maximum size allowed for uploaded files.
    - **`50MB`**: Files larger than 50MB will be rejected.
  
- **Related Properties**:
  - **`spring.servlet.multipart.max-request-size`**: Maximum size allowed for multipart/form-data requests.
  - **`spring.servlet.multipart.enabled`**: Enables or disables multipart support.
  
- **Example Usage**:
  ```yaml
  spring:
    servlet:
      multipart:
        max-file-size: 100MB
        max-request-size: 100MB
        enabled: true
  ```

- **Practical Example**:
  - **Controller Handling File Upload**:
    ```java
    @RestController
    @RequestMapping("/files")
    public class FileController {
      
        @PostMapping("/upload")
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }
            // Handle file storage
            return ResponseEntity.ok("File uploaded successfully");
        }
    }
    ```

### **2.3. SpringDoc OpenAPI Configuration**

```yaml
springdoc:
  default-produces-media-type: application/json
```

- **Purpose**: Configures SpringDoc OpenAPI, which automatically generates API documentation.
- **Explanation**:
  - **`springdoc.default-produces-media-type`**: Sets the default `Content-Type` for API responses.
    - **`application/json`**: Indicates that APIs produce JSON responses by default.
  
- **Related Properties**:
  - **`springdoc.api-docs.path`**: Path where the OpenAPI documentation is served (default is `/v3/api-docs`).
  - **`springdoc.swagger-ui.path`**: Path for accessing Swagger UI (default is `/swagger-ui.html`).

- **Example Usage**:
  ```yaml
  springdoc:
    api-docs:
      path: /api-docs
    swagger-ui:
      path: /swagger-ui.html
    default-produces-media-type: application/json
  ```

- **Practical Example**:
  - After starting the application, access [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to view the interactive API documentation.
  - **Controller with OpenAPI Annotations**:
    ```java
    @RestController
    @RequestMapping("/api/books")
    @Tag(name = "Books", description = "Operations related to books")
    public class BookController {
      
        @Operation(summary = "Get all books")
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
        })
        @GetMapping
        public ResponseEntity<List<Book>> getAllBooks() {
            List<Book> books = bookService.findAllBooks();
            return ResponseEntity.ok(books);
        }
        
        // Other endpoints
    }
    ```

### **2.4. Server Configuration**

```yaml
server:
  servlet:
    context-path: /api/v1/
```

- **Purpose**: Sets the base URL path for the application.
- **Explanation**:
  - **`server.servlet.context-path`**: Defines a common prefix for all endpoints in the application.
    - **`/api/v1/`**: All your REST endpoints will be accessible under this path.
  
- **Effect**:
  - **Example**:
    - If you have a controller mapped to `/books`, the full URL becomes `/api/v1/books`.
  
- **Example Usage**:
  ```yaml
  server:
    servlet:
      context-path: /myapp/
  ```

  - **Result**:
    - Controller mapped to `/users` becomes accessible at `/myapp/users`.

- **Practical Considerations**:
  - **Versioning APIs**: Using `/api/v1/` allows for future versions (e.g., `/api/v2/`) without breaking existing clients.
  - **Consistent Base Path**: Simplifies reverse proxy configurations and API gateway routing.

---

## **3. Understanding `application-dev.yml`**

The `application-dev.yml` file contains configurations specific to the development environment. These settings override or extend those in `application.yml` when the `dev` profile is active.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/book_social_network
    username: username
    password: password
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
  mail:
    host: localhost
    port: 1025
    username: username
    password: password
    properties:
      mail:
        smtp:
          trust: "*"
        auth: true
        starttls:
          enable: true
        connectiontimeout: 5000
        timeout: 3000
        writetimeout: 5000
```

### **3.1. DataSource Configuration**

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/book_social_network
    username: username
    password: password
    driver-class-name: org.postgresql.Driver
```

- **Purpose**: Configures the database connection for the application.
- **Explanation**:
  - **`spring.datasource.url`**: JDBC URL to connect to the PostgreSQL database.
    - **`jdbc:postgresql://localhost:5432/book_social_network`**:
      - **Host**: `localhost` (in development, assuming the database runs on the local machine).
      - **Port**: `5432` (default PostgreSQL port).
      - **Database**: `book_social_network`.
  
  - **`spring.datasource.username` & `spring.datasource.password`**: Credentials for authenticating with PostgreSQL.
  
  - **`spring.datasource.driver-class-name`**: Specifies the JDBC driver. Not strictly necessary as Spring Boot can infer it, but included for clarity.
    - **`org.postgresql.Driver`**: PostgreSQL JDBC driver.

- **Practical Example**:
  - **Connecting to Dockerized PostgreSQL**:
    - If PostgreSQL is running in a Docker container with `container_name: postgres-sql-bsn`, update the URL accordingly:
      ```yaml
      spring:
        datasource:
          url: jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
      ```
    - **Note**: Replace `localhost` with the Docker service name (`postgres-sql-bsn`) to enable inter-container communication.

- **Security Considerations**:
  - **Avoid Hardcoding Credentials**:
    - Use environment variables or external secret management tools.
    - **Example with Environment Variables**:
      ```yaml
      spring:
        datasource:
          url: ${DB_URL}
          username: ${DB_USERNAME}
          password: ${DB_PASSWORD}
      ```

### **3.2. JPA (Hibernate) Configuration**

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

- **Purpose**: Configures JPA (Java Persistence API) and Hibernate settings for ORM (Object-Relational Mapping).
- **Explanation**:
  - **`spring.jpa.hibernate.ddl-auto`**:
    - **`update`**: Automatically updates the database schema to match entity definitions without dropping tables.
    - **Other Options**:
      - **`none`**: No action.
      - **`validate`**: Validates the schema without making changes.
      - **`create`**: Creates the schema, dropping existing data.
      - **`create-drop`**: Creates the schema on startup and drops it on shutdown.
  
  - **`spring.jpa.show-sql`**:
    - **`false`**: Disables logging of SQL statements.
    - **`true`**: Enables logging of SQL for debugging.
  
  - **`spring.jpa.properties.hibernate.format_sql`**:
    - **`true`**: Formats the SQL statements in logs for readability.
  
  - **`spring.jpa.database`**:
    - **`postgresql`**: Specifies the database type.
  
  - **`spring.jpa.database-platform`**:
    - **`org.hibernate.dialect.PostgreSQLDialect`**: Specifies the Hibernate dialect for PostgreSQL, optimizing SQL generation.

- **Practical Example**:
  - **Entity Definition**:
    ```java
    @Entity
    public class Book {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank
        private String title;

        @NotBlank
        private String author;

        private String description;

        // Getters and Setters
    }
    ```
  - **Result**:
    - On application startup, Hibernate scans entities and updates the `book_social_network` database schema accordingly.

- **Caution with `ddl-auto=update`**:
  - **Development Use**: Suitable for development where schema changes are frequent.
  - **Production Use**: Not recommended. Prefer managed migrations with tools like Flyway or Liquibase to have controlled, versioned schema changes.

### **3.3. Mail Configuration**

```yaml
spring:
  mail:
    host: localhost
    port: 1025
    username: username
    password: password
    properties:
      mail:
        smtp:
          trust: "*"
        auth: true
        starttls:
          enable: true
        connectiontimeout: 5000
        timeout: 3000
        writetimeout: 5000
```

- **Purpose**: Configures the mail server settings for sending emails from the application.
- **Explanation**:
  - **`spring.mail.host`**: The SMTP server host.
    - **`localhost`**: In development, pointing to a local mail server or a Dockerized service like MailDev.
  
  - **`spring.mail.port`**: The SMTP server port.
    - **`1025`**: Commonly used by MailDev for SMTP.
  
  - **`spring.mail.username` & `spring.mail.password`**:
    - Credentials for SMTP authentication.
    - **In MailDev**: Typically not required or can be left blank.
  
  - **`spring.mail.properties.mail.smtp.trust`**:
    - **`*`**: Trusts all hosts. Useful in development to bypass SSL certificate issues.
  
  - **`spring.mail.properties.mail.smtp.auth`**:
    - **`true`**: Enables SMTP authentication.
  
  - **`spring.mail.properties.mail.smtp.starttls.enable`**:
    - **`true`**: Enables STARTTLS for secure communication.
    - **Note**: MailDev may not require TLS; adjust based on the mail server used.
  
  - **`spring.mail.properties.mail.smtp.connectiontimeout`**:
    - **`5000`**: Connection timeout in milliseconds.
  
  - **`spring.mail.properties.mail.smtp.timeout`**:
    - **`3000`**: I/O timeout in milliseconds.
  
  - **`spring.mail.properties.mail.smtp.writetimeout`**:
    - **`5000`**: Write timeout in milliseconds.

- **Practical Example**:
  - **Using MailDev**:
    - **Configuration**:
      ```yaml
      spring:
        mail:
          host: localhost
          port: 1025
          username:
          password:
          properties:
            mail:
              smtp:
                trust: "*"
              auth: false
              starttls:
                enable: false
      ```
    - **Reason**: MailDev doesn't require authentication or TLS, simplifying email testing.
  
  - **Sending Emails**:
    ```java
    @Service
    public class EmailService {
      
        @Autowired
        private JavaMailSender mailSender;

        public void sendWelcomeEmail(String to, String username) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Welcome to Book Social Network!");
            message.setText("Hello " + username + ", welcome to our platform.");
            mailSender.send(message);
        }
    }
    ```
    - **Result**: Emails are captured by MailDev and accessible via its web interface (e.g., [http://localhost:1080](http://localhost:1080)) without being sent to real email addresses.

- **Adjusting for Different Mail Servers**:
  - **Using Gmail SMTP**:
    ```yaml
    spring:
      mail:
        host: smtp.gmail.com
        port: 587
        username: your-email@gmail.com
        password: your-email-password
        properties:
          mail:
            smtp:
              trust: "smtp.gmail.com"
            auth: true
            starttls:
              enable: true
            connectiontimeout: 5000
            timeout: 3000
            writetimeout: 5000
    ```
    - **Note**: Ensure less secure app access is enabled or use App Passwords for Gmail.

---

## **4. Integration with Docker Compose**

Your **Docker Compose** setup includes two primary services:

1. **PostgreSQL**: Database service.
2. **MailDev**: Development mail server.

Let's see how these services integrate with your Spring Boot application's configurations.

### **4.1. PostgreSQL Integration**

- **Docker Compose Service**:
  ```yaml
  services:
    postgres:
      container_name: postgres-sql-bsn
      image: postgres:16
      environment:
        POSTGRES_USER: username
        POSTGRES_PASSWORD: password
        PG_DATA: /var/lib/postgresql/data
        POSTGRES_DB: book_social_network
      volumes:
        - postgres:/data/postgres
      ports:
        - "5432:5432"
      networks:
        - spring-demo
      restart: unless-stopped
  ```
  
- **Spring Boot Configuration**:
  ```yaml
  spring:
    datasource:
      url: jdbc:postgresql://localhost:5432/book_social_network
      username: username
      password: password
      driver-class-name: org.postgresql.Driver
    # ... other properties
  ```

- **Integration Steps**:
  1. **Start PostgreSQL Service**:
     ```bash
     docker-compose up -d postgres
     ```
  
  2. **Ensure Database is Accessible**:
     - **From Host**: Via `localhost:5432`.
     - **From Other Containers**: Via service name `postgres-sql-bsn:5432`.
  
  3. **Update Spring Boot Configuration (if necessary)**:
     - If Spring Boot runs on the host, `localhost:5432` is correct.
     - If Spring Boot runs inside another Docker container on the same network (`spring-demo`), use `jdbc:postgresql://postgres-sql-bsn:5432/book_social_network`.
  
  4. **Example Dockerized Spring Boot Service**:
     ```yaml
     services:
       app:
         build: .
         container_name: book-network-app
         image: book-network:latest
         environment:
           SPRING_DATASOURCE_URL: jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
           SPRING_DATASOURCE_USERNAME: username
           SPRING_DATASOURCE_PASSWORD: password
         ports:
           - "8080:8080"
         networks:
           - spring-demo
         depends_on:
           - postgres
           - mail-dev
         restart: unless-stopped
     ```
  
  - **Explanation**:
    - **Environment Variables**: Overrides Spring Boot's `application-dev.yml` properties to use Docker service names.
    - **`depends_on`**: Ensures `postgres` starts before `app`.
  
  - **Spring Boot Configuration Update**:
    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
        username: username
        password: password
        driver-class-name: org.postgresql.Driver
      # ... other properties
    ```

### **4.2. MailDev Integration**

- **Docker Compose Service**:
  ```yaml
  services:
    mail-dev:
      container_name: mail-dev-bsn
      image: maildev/maildev
      ports:
        - "1080:1080"
        - "1025:1025"
  ```
  
- **Spring Boot Configuration**:
  ```yaml
  spring:
    mail:
      host: localhost
      port: 1025
      username: username
      password: password
      properties:
        mail:
          smtp:
            trust: "*"
          auth: true
          starttls:
            enable: true
          connectiontimeout: 5000
          timeout: 3000
          writetimeout: 5000
  ```
  
- **Integration Steps**:
  1. **Start MailDev Service**:
     ```bash
     docker-compose up -d mail-dev
     ```
  
  2. **Access MailDev Web Interface**:
     - Navigate to [http://localhost:1080](http://localhost:1080) to view sent emails.
  
  3. **Configure Spring Boot**:
     - **Host**: `localhost` (if Spring Boot runs on the host).
     - **Port**: `1025` (MailDev's SMTP port).
  
  - **If Spring Boot Runs Inside Docker**:
    - Use service name `mail-dev-bsn`:
      ```yaml
      spring:
        mail:
          host: mail-dev-bsn
          port: 1025
          username:
          password:
          properties:
            mail:
              smtp:
                trust: "*"
              auth: false
              starttls:
                enable: false
      ```
    - **Note**: MailDev in Docker doesn't require authentication or TLS.

- **Example Spring Boot Service in Docker**:
  ```yaml
  services:
    app:
      build: .
      container_name: book-network-app
      image: book-network:latest
      environment:
        SPRING_DATASOURCE_URL: jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
        SPRING_DATASOURCE_USERNAME: username
        SPRING_DATASOURCE_PASSWORD: password
        SPRING_MAIL_HOST: mail-dev-bsn
        SPRING_MAIL_PORT: 1025
      ports:
        - "8080:8080"
      networks:
        - spring-demo
      depends_on:
        - postgres
        - mail-dev
      restart: unless-stopped
  ```

- **Practical Example**:
  - **Sending a Welcome Email**:
    1. **Trigger Email**: Register a new user, which calls `sendWelcomeEmail()`.
    2. **MailDev Captures Email**: The email is not sent to a real address but is captured by MailDev.
    3. **View Email**: Access [http://localhost:1080](http://localhost:1080) to see the email content, subject, and recipient.

---

## **5. Practical Examples and Usage**

Let's explore how these configurations work in practice, including switching profiles, handling file uploads, accessing API documentation, and sending emails during development.

### **5.1. Switching Between Profiles**

**Active Profile**: Defined in `application.yml` as `dev`.

**Scenario**: Switching to a `prod` profile for production deployment.

1. **Create `application-prod.yml`**:
   ```yaml
   spring:
     datasource:
       url: jdbc:postgresql://prod-db-server:5432/book_social_network
       username: prod_user
       password: prod_password
       driver-class-name: org.postgresql.Driver
     jpa:
       hibernate:
         ddl-auto: validate
       show-sql: false
       properties:
         hibernate:
           format_sql: false
       database: postgresql
       database-platform: org.hibernate.dialect.PostgreSQLDialect
     mail:
       host: smtp.prod-mail-server.com
       port: 587
       username: prod_email_user
       password: prod_email_password
       properties:
         mail:
           smtp:
             trust: "smtp.prod-mail-server.com"
           auth: true
           starttls:
             enable: true
           connectiontimeout: 10000
           timeout: 5000
           writetimeout: 10000
   ```

2. **Update `application.yml` to Use `prod` Profile**:
   ```yaml
   spring:
     profiles:
       active: prod
     # ... other configurations
   ```

3. **Deploying with `prod` Profile**:
   - **Docker Compose Adjustment**:
     - If deploying via Docker Compose, set the `SPRING_PROFILES_ACTIVE` environment variable to `prod`.
     ```yaml
     services:
       app:
         environment:
           SPRING_PROFILES_ACTIVE: prod
           # ... other environment variables
     ```
   - **Command-Line Override**:
     - Run the application with a different profile.
     ```bash
     java -jar book-network-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
     ```

4. **Benefits**:
   - **Separation of Concerns**: Different configurations for development and production.
   - **Controlled Schema Changes**: Using `ddl-auto=validate` in production to prevent unintended schema updates.

### **5.2. Handling File Uploads**

**Configuration**:
```yaml
spring:
  servlet:
    multipart:
      max-file-size: 50MB
```

**Scenario**: Uploading book cover images to the application.

1. **Controller to Handle File Upload**:
   ```java
   @RestController
   @RequestMapping("/api/books")
   public class BookController {
     
       @PostMapping("/upload")
       public ResponseEntity<String> uploadBookCover(@RequestParam("file") MultipartFile file) {
           if (file.isEmpty()) {
               return ResponseEntity.badRequest().body("Please select a file to upload.");
           }
           
           // Save the file to the desired location
           try {
               byte[] bytes = file.getBytes();
               Path path = Paths.get("uploads/" + file.getOriginalFilename());
               Files.write(path, bytes);
           } catch (IOException e) {
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
           }
           
           return ResponseEntity.ok("File uploaded successfully.");
       }
   }
   ```

2. **Uploading a File**:
   - **Using Postman**:
     - **Method**: POST
     - **URL**: `http://localhost:8080/api/v1/books/upload`
     - **Body**: Form-data with key `file` and attach the desired file.
  
3. **Handling File Size Limits**:
   - **Beyond 50MB**:
     - If a user tries to upload a file larger than 50MB, Spring Boot will reject the request with a `MaxUploadSizeExceededException`.
     - **Global Exception Handler**:
       ```java
       @RestControllerAdvice
       public class GlobalExceptionHandler {
         
           @ExceptionHandler(MaxUploadSizeExceededException.class)
           public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
               return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("File too large!");
           }
           
           // Other exception handlers
       }
       ```

### **5.3. Accessing API Documentation**

**Configuration**:
```yaml
springdoc:
  default-produces-media-type: application/json
```

**Scenario**: Viewing and interacting with API endpoints using Swagger UI.

1. **Ensure SpringDoc Dependency**:
   - Make sure `springdoc-openapi-starter-webmvc-ui` is included in your `pom.xml`.
     ```xml
     <dependency>
         <groupId>org.springdoc</groupId>
         <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
         <version>2.3.0</version>
     </dependency>
     ```

2. **Accessing Swagger UI**:
   - **URL**: `http://localhost:8080/swagger-ui.html` or `http://localhost:8080/swagger-ui/index.html`
  
3. **Interacting with APIs**:
   - Swagger UI provides an interactive interface to test API endpoints without external tools.
   - **Example**:
     - **GET /api/v1/books**: Fetch all books.
     - **POST /api/v1/books**: Add a new book.
     - **POST /api/v1/books/upload**: Upload a book cover image.

4. **Customizing OpenAPI Documentation**:
   - **Adding Descriptions and Tags**:
     ```java
     @RestController
     @RequestMapping("/api/books")
     @Tag(name = "Books", description = "Operations related to books")
     public class BookController {
         
         @Operation(summary = "Get all books", description = "Retrieve a list of all available books.")
         @GetMapping
         public ResponseEntity<List<Book>> getAllBooks() {
             // ...
         }
         
         // Other endpoints
     }
     ```

5. **Benefits**:
   - **Documentation**: Automatically generated, keeping documentation up-to-date with code.
   - **Testing**: Easily test APIs during development.
   - **Client Generation**: Generate client code in various languages based on the OpenAPI spec.

### **5.4. Sending Emails During Development**

**Configuration**:
```yaml
spring:
  mail:
    host: localhost
    port: 1025
    username: username
    password: password
    properties:
      mail:
        smtp:
          trust: "*"
        auth: true
        starttls:
          enable: true
        connectiontimeout: 5000
        timeout: 3000
        writetimeout: 5000
```

**Scenario**: Sending welcome emails to users upon registration.

1. **MailDev Setup**:
   - **Start MailDev Service**:
     ```bash
     docker-compose up -d mail-dev
     ```
  
   - **Access MailDev UI**:
     - **URL**: [http://localhost:1080](http://localhost:1080)
  
2. **Email Service Implementation**:
   ```java
   @Service
   public class EmailService {
       
       @Autowired
       private JavaMailSender mailSender;

       public void sendWelcomeEmail(String to, String username) {
           SimpleMailMessage message = new SimpleMailMessage();
           message.setTo(to);
           message.setSubject("Welcome to Book Social Network!");
           message.setText("Hello " + username + ", welcome to our platform.");
           mailSender.send(message);
       }
   }
   ```

3. **Controller Triggering Email**:
   ```java
   @RestController
   @RequestMapping("/api/users")
   public class UserController {
       
       @Autowired
       private EmailService emailService;
       
       @PostMapping("/register")
       public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
           // Registration logic (save user to database)
           
           // Send welcome email
           emailService.sendWelcomeEmail(userDto.getEmail(), userDto.getUsername());
           
           return ResponseEntity.ok("User registered successfully");
       }
   }
   ```

4. **Viewing Sent Emails**:
   - After registering a user, navigate to [http://localhost:1080](http://localhost:1080) to see the captured email.
  
5. **Adjusting SMTP Properties for MailDev**:
   - **Disable Authentication and TLS** (if MailDev doesn't require them):
     ```yaml
     spring:
       mail:
         host: localhost
         port: 1025
         username:
         password:
         properties:
           mail:
             smtp:
               trust: "*"
             auth: false
             starttls:
               enable: false
             connectiontimeout: 5000
             timeout: 3000
             writetimeout: 5000
     ```

---

## **6. Best Practices and Recommendations**

Adhering to best practices ensures your application is secure, maintainable, and scalable. Here's how you can enhance your configuration:

### **6.1. Securely Managing Secrets**

- **Avoid Hardcoding Credentials**:
  - Do not store sensitive information directly in configuration files, especially if they're part of version control.
  
- **Use Environment Variables**:
  - **Example**:
    ```yaml
    spring:
      datasource:
        url: ${DB_URL}
        username: ${DB_USERNAME}
        password: ${DB_PASSWORD}
      mail:
        host: ${MAIL_HOST}
        port: ${MAIL_PORT}
        username: ${MAIL_USERNAME}
        password: ${MAIL_PASSWORD}
    ```
  - **Setting Environment Variables**:
    - **Unix/Linux**:
      ```bash
      export DB_URL=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
      export DB_USERNAME=username
      export DB_PASSWORD=password
      export MAIL_HOST=mail-dev-bsn
      export MAIL_PORT=1025
      export MAIL_USERNAME=username
      export MAIL_PASSWORD=password
      ```
    - **Windows**:
      ```cmd
      set DB_URL=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
      set DB_USERNAME=username
      set DB_PASSWORD=password
      set MAIL_HOST=mail-dev-bsn
      set MAIL_PORT=1025
      set MAIL_USERNAME=username
      set MAIL_PASSWORD=password
      ```

- **Use `.env` Files with Docker Compose**:
  - **Create a `.env` File**:
    ```env
    DB_URL=jdbc:postgresql://postgres-sql-bsn:5432/book_social_network
    DB_USERNAME=username
    DB_PASSWORD=password
    MAIL_HOST=mail-dev-bsn
    MAIL_PORT=1025
    MAIL_USERNAME=username
    MAIL_PASSWORD=password
    ```
  - **Reference in `docker-compose.yml`**:
    ```yaml
    services:
      app:
        environment:
          SPRING_DATASOURCE_URL: ${DB_URL}
          SPRING_DATASOURCE_USERNAME: ${DB_USERNAME}
          SPRING_DATASOURCE_PASSWORD: ${DB_PASSWORD}
          SPRING_MAIL_HOST: ${MAIL_HOST}
          SPRING_MAIL_PORT: ${MAIL_PORT}
          SPRING_MAIL_USERNAME: ${MAIL_USERNAME}
          SPRING_MAIL_PASSWORD: ${MAIL_PASSWORD}
    ```
  - **Security Tip**: Add `.env` to `.gitignore` to prevent committing sensitive data.
    ```gitignore
    # .gitignore
    .env
    ```

### **6.2. Externalizing Configuration**

- **Use Separate Configuration Files for Different Environments**:
  - Maintain `application-dev.yml`, `application-prod.yml`, etc., to segregate environment-specific settings.
  
- **Leverage Spring Boot's Config Hierarchy**:
  - Properties defined in `application-dev.yml` override those in `application.yml` when the `dev` profile is active.
  
- **Example**:
  - **`application.yml`**:
    ```yaml
    server:
      port: 8080
    spring:
      profiles:
        active: dev
      datasource:
        url: jdbc:postgresql://localhost:5432/book_social_network
        username: username
        password: password
    ```
  - **`application-prod.yml`**:
    ```yaml
    server:
      port: 80
    spring:
      datasource:
        url: jdbc:postgresql://prod-db-server:5432/book_social_network
        username: prod_user
        password: prod_password
    ```

### **6.3. Optimizing Performance**

- **Adjust Hibernate Settings**:
  - **`hibernate.show_sql`**: Disable in production to reduce logging overhead.
    ```yaml
    spring:
      jpa:
        show-sql: false
    ```
  
  - **`hibernate.format_sql`**: Disable formatting in production for performance.
    ```yaml
    spring:
      jpa:
        properties:
          hibernate:
            format_sql: false
    ```
  
  - **`ddl-auto` Setting**:
    - Use `validate` or `none` in production to prevent automatic schema changes.
    - Implement controlled migrations with Flyway or Liquibase.

- **Configure Connection Pooling**:
  - **Spring Boot uses HikariCP by default**:
    - **Example**:
      ```yaml
      spring:
        datasource:
          hikari:
            maximum-pool-size: 20
            minimum-idle: 5
            idle-timeout: 30000
            connection-timeout: 30000
      ```

- **Enable Caching**:
  - Utilize caching for frequently accessed data to reduce database load.
    ```yaml
    spring:
      cache:
        type: redis
    ```
  
  - **Integration with Redis**:
    - Add Redis dependency.
      ```xml
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-data-redis</artifactId>
      </dependency>
      ```
    - Configure Redis properties.
      ```yaml
      spring:
        redis:
          host: redis-server
          port: 6379
      ```

### **6.4. Comprehensive Logging**

- **Configure Logging Levels**:
  - **Example**:
    ```yaml
    logging:
      level:
        root: INFO
        com.wchamara.booknetwork: DEBUG
    ```
  
  - **Explanation**:
    - **`root: INFO`**: Sets the default logging level to INFO.
    - **`com.wchamara.booknetwork: DEBUG`**: Enables DEBUG level logging for your application's packages.

- **Customize Log Formats**:
  ```yaml
  logging:
    pattern:
      console: "%d{yyyy-MM-dd HH:mm:ss} - %logger{36} - %msg%n"
  ```

- **External Log Configuration**:
  - Use `logback-spring.xml` or `log4j2-spring.xml` for advanced logging configurations.
  
- **Practical Example**:
  - **Logback Configuration (`logback-spring.xml`)**:
    ```xml
    <configuration>
        <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
            <encoder>
                <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
            </encoder>
        </appender>

        <logger name="com.wchamara.booknetwork" level="DEBUG" />

        <root level="INFO">
            <appender-ref ref="STDOUT" />
        </root>
    </configuration>
    ```

---

## **7. Conclusion**

Your Spring Boot application's configuration is thoughtfully segmented into general settings (`application.yml`) and environment-specific settings (`application-dev.yml`). This approach ensures flexibility, maintainability, and scalability across different environments.

**Key Takeaways**:

- **Profiles**: Utilize Spring Boot profiles to manage environment-specific configurations effectively.
- **Externalized Configuration**: Avoid hardcoding sensitive information; leverage environment variables and `.env` files.
- **Integration with Docker Compose**: Seamlessly connect your application to Dockerized services like PostgreSQL and MailDev.
- **Best Practices**: Emphasize security, performance optimization, and comprehensive logging to enhance application robustness.

By understanding and implementing these configurations, you ensure that your **Book Social Network** application is well-equipped to handle development needs and is poised for smooth transitions to production environments.

---

## **8. Additional Resources**

To further enhance your understanding and application of Spring Boot configurations and Docker integration, consider exploring the following resources:

1. **Spring Boot Documentation**:
   - [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
   - [Externalized Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config)
  
2. **SpringDoc OpenAPI**:
   - [SpringDoc Official Documentation](https://springdoc.org/)
   - [Integrating SpringDoc with Spring Boot](https://springdoc.org/quickstart.html)
  
3. **Docker Compose Documentation**:
   - [Docker Compose Overview](https://docs.docker.com/compose/)
   - [Best Practices for Docker Compose](https://docs.docker.com/compose/best-practices/)
  
4. **Flyway and Liquibase for Database Migrations**:
   - [Flyway Documentation](https://flywaydb.org/documentation/)
   - [Liquibase Documentation](https://www.liquibase.org/documentation/index.html)
  
5. **Spring Boot Security**:
   - [Spring Security Reference](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
   - [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
  
6. **Spring Boot File Upload**:
   - [Handling File Uploads with Spring Boot](https://www.baeldung.com/spring-boot-file-upload)
  
7. **Logging in Spring Boot**:
   - [Spring Boot Logging Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging)

Feel free to explore these resources to deepen your understanding and enhance your application's capabilities.

# 📗Authentication Flow

![alt text](image.png)

The diagram you shared outlines a security and request handling flow, likely from a Spring Security-based web application. Let me break it down step by step and correlate it with the `pom.xml` provided for better understanding:

### Flow Breakdown:

1. **HTTP Request:**
   - Requests (`GET`, `POST`, `PATCH`, `DELETE`) are sent to your backend. In Spring Boot, this would likely be handled by controllers.
   - In the diagram, the `DispatcherServlet` routes the request to the appropriate controller (`ControllerA`, `ControllerB`, etc.), which is part of the Spring MVC architecture.

2. **Security Filters:**
   - The **FilterChain** applies multiple filters to incoming requests. One of them is the `JwtAuthenticationFilter` (likely subclassing `OncePerRequestFilter`). This filter checks for a JWT (JSON Web Token) in the request header.
   
   - The `OncePerRequestFilter` ensures that this filter is only executed once per request.

3. **JWT Validation:**
   - The `JwtAuthenticationFilter` delegates the token validation to `JwtService`. If a valid token is found, it retrieves user details.
   - In case of an invalid token, a `TokenInvalidException` is thrown.

4. **UserDetailsService:**
   - Once the token is validated, the `UserDetailsService` attempts to find the user based on the email embedded in the token.
   - If the user is not found, a `UserNotFoundException` is thrown.

5. **SecurityContextHolder:**
   - If the user is authenticated successfully, the user details (including `Principal`, `Credentials`, and `Authorities`) are stored in `SecurityContextHolder`.
   - This security context is then available for use by the controllers to handle authorization logic.

6. **Response Handling:**
   - Once the request has passed through the filters and validation checks, the `DispatcherServlet` directs the request to the appropriate controller to process the business logic and generate a response.

### `pom.xml` Correlation:

In the `pom.xml`, we see the following dependencies related to the flow described in the diagram:

1. **Spring Security:**
   - The `spring-boot-starter-security` dependency provides the Spring Security framework, which includes filters, security context management, and authentication mechanisms.
   - The `spring-security-test` dependency is used for testing security-related components.

   Example:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-security</artifactId>
   </dependency>
   ```

2. **JWT Handling:**
   - The JWT processing is handled by `jjwt-api`, `jjwt-impl`, and `jjwt-jackson` dependencies. These provide the necessary functionality to create, parse, and validate JWT tokens.
   
   Example:
   ```xml
   <dependency>
       <groupId>io.jsonwebtoken</groupId>
       <artifactId>jjwt-api</artifactId>
       <version>0.12.3</version>
   </dependency>
   ```

3. **Controller Layer:**
   - The `spring-boot-starter-web` is responsible for creating controllers (e.g., `ControllerA`, `ControllerB`) that map HTTP requests to business logic.
   
   Example:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-web</artifactId>
   </dependency>
   ```

4. **Thymeleaf:**
   - The `spring-boot-starter-thymeleaf` is used for server-side rendering of HTML pages, often used in conjunction with controllers to return views.

5. **Database Interaction:**
   - The `spring-boot-starter-data-jpa` is used to interact with databases, most likely tied to the `UserDetailsService` that fetches user data during the JWT validation process.
   - The PostgreSQL driver (`postgresql`) is also included for database interaction.
   
   Example:
   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-jpa</artifactId>
   </dependency>
   ```

### Example Code Snippets:

1. **JWT Filter Example (`JwtAuthenticationFilter`):**
   This filter checks for the JWT in the request header, validates it, and sets the authentication context.

   ```java
   public class JwtAuthenticationFilter extends OncePerRequestFilter {

       @Autowired
       private JwtService jwtService;

       @Autowired
       private UserDetailsServiceImpl userDetailsService;

       @Override
       protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
           String token = extractJwtFromRequest(request);

           if (StringUtils.hasText(token) && jwtService.validateToken(token)) {
               String email = jwtService.extractEmail(token);

               UserDetails userDetails = userDetailsService.loadUserByUsername(email);
               UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                       userDetails, null, userDetails.getAuthorities());

               SecurityContextHolder.getContext().setAuthentication(authentication);
           }

           filterChain.doFilter(request, response);
       }

       private String extractJwtFromRequest(HttpServletRequest request) {
           String bearerToken = request.getHeader("Authorization");
           if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
               return bearerToken.substring(7);
           }
           return null;
       }
   }
   ```

2. **JWT Service Example:**

   This service handles the logic for token validation and extraction.

   ```java
   @Service
   public class JwtService {

       private final String secretKey = "secret";

       public boolean validateToken(String token) {
           try {
               Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
               return true;
           } catch (JwtException | IllegalArgumentException e) {
               return false;
           }
       }

       public String extractEmail(String token) {
           Claims claims = Jwts.parser()
               .setSigningKey(secretKey)
               .parseClaimsJws(token)
               .getBody();
           return claims.getSubject();
       }
   }
   ```

3. **Security Configuration:**

   The Spring Security configuration ensures that the `JwtAuthenticationFilter` is applied before other filters.

   ```java
   @Configuration
   @EnableWebSecurity
   public class SecurityConfig extends WebSecurityConfigurerAdapter {

       @Autowired
       private JwtAuthenticationFilter jwtAuthenticationFilter;

       @Override
       protected void configure(HttpSecurity http) throws Exception {
           http
               .csrf().disable()
               .authorizeRequests()
               .antMatchers("/api/auth/**").permitAll()
               .anyRequest().authenticated()
               .and()
               .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
       }
   }
   ```

### Conclusion:

This architecture provides robust security using JWT for authentication. It filters each request, validates tokens, fetches user details, and secures user contexts. All dependencies in the `pom.xml` support these processes, especially JWT handling (`jjwt`) and security (`spring-boot-starter-security`).


# 📗User,Token and Role Entities

Certainly! Let's delve deeply into the provided Java code snippets, which form the backbone of a Spring Boot application centered around user management, roles, and token handling. We'll explore each component, understand their interactions, and provide examples to illustrate their functionality.

---

## 1. **Main Application Class**

### **Code Overview**

```java
package com.wchamara.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookNetworkApplication.class, args);
    }

}
```

### **Explanation**

- **`@SpringBootApplication`**: This is a convenience annotation that adds all of the following:
  - **`@Configuration`**: Tags the class as a source of bean definitions.
  - **`@EnableAutoConfiguration`**: Enables Spring Boot’s auto-configuration mechanism.
  - **`@ComponentScan`**: Enables component scanning so that the web controller classes and other components you create will be automatically discovered and registered as beans in Spring’s application context.

- **`@EnableJpaAuditing`**: Enables JPA Auditing in the application. This allows the use of annotations like `@CreatedDate` and `@LastModifiedDate` to automatically populate auditing fields in entities.

- **`main` Method**: The entry point of the Spring Boot application. It bootstraps the application, starting the embedded server and initializing the Spring context.

### **Example**

When you run this class, Spring Boot initializes the application context, sets up all the necessary configurations, scans for components (like controllers, services, repositories), and starts the embedded server (e.g., Tomcat) to listen for incoming HTTP requests.

---

## 2. **Role Entity**

### **Code Overview**

```java
package com.wchamara.book.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wchamara.book.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String name;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDateTime;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;
}
```

### **Explanation**

- **Lombok Annotations**:
  - **`@Getter` & `@Setter`**: Automatically generate getter and setter methods for all fields.
  - **`@Builder`**: Implements the builder pattern for the class, facilitating easier object creation.
  - **`@AllArgsConstructor` & `@NoArgsConstructor`**: Generate constructors with all arguments and a no-argument constructor, respectively.

- **JPA Annotations**:
  - **`@Entity`**: Marks the class as a JPA entity mapped to a database table.
  - **`@Id` & `@GeneratedValue`**: Specifies the primary key and its generation strategy.
  - **`@Column(unique = true)`**: Ensures that the `name` field is unique in the database.
  - **`@ManyToMany(mappedBy = "roles")`**: Defines a many-to-many relationship with the `User` entity, indicating that the `users` field is the inverse side of the relationship.

- **Auditing**:
  - **`@CreatedDate`**: Automatically populates the `createdDate` field when the entity is first persisted.
  - **`@LastModifiedDate`**: Automatically updates the `lastModifiedDateTime` field whenever the entity is updated.
  - **`@EntityListeners(AuditingEntityListener.class)`**: Registers the auditing entity listener to handle the auditing annotations.

- **`@JsonIgnore`**: Prevents the `users` list from being serialized into JSON, avoiding potential infinite recursion issues during serialization.

### **Example Usage**

**Creating a Role:**

```java
Role adminRole = Role.builder()
    .name("ADMIN")
    .build();
roleRepository.save(adminRole);
```

**Associating Roles with Users:**

Suppose you have a `User` entity and you want to assign the "ADMIN" role to a user.

```java
User user = userRepository.findById(userId).orElseThrow();
Role adminRole = roleRepository.findByName("ADMIN").orElseThrow();
user.getRoles().add(adminRole);
userRepository.save(user);
```

This establishes a many-to-many relationship between the `User` and `Role` entities.

---

## 3. **Empty Code Block**

```java
```java

```

### **Explanation**

This code block is empty. It might have been included by mistake or intended for future content. If there's specific content you intended to share or discuss here, please provide it for further assistance.

---

## 4. **Token Entity**

### **Code Overview**

```java
package com.wchamara.book.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue
    private Integer id;

    private String token;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validatedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "userId")
    private User user;
}
```

### **Explanation**

- **Lombok Annotations**:
  - Similar to the `Role` entity, Lombok is used to generate boilerplate code like getters, setters, constructors, and the builder pattern.

- **JPA Annotations**:
  - **`@Entity`**: Marks the class as a JPA entity.
  - **`@Id` & `@GeneratedValue`**: Define the primary key and its generation strategy.
  - **`@ManyToOne`**: Establishes a many-to-one relationship with the `User` entity, meaning multiple tokens can be associated with a single user.
  - **`@JoinColumn`**: Specifies the foreign key column (`userId`) in the `Token` table that references the `User` entity. The `nullable = false` constraint ensures that every token is associated with a user.

- **Fields**:
  - **`token`**: Represents the actual token string (e.g., JWT, UUID).
  - **`createdAt`**: Timestamp when the token was created.
  - **`expiresAt`**: Timestamp when the token expires.
  - **`validatedAt`**: Timestamp when the token was validated or used.

### **Example Usage**

**Generating and Saving a Token:**

```java
User user = userRepository.findById(userId).orElseThrow();
String tokenString = UUID.randomUUID().toString();
LocalDateTime now = LocalDateTime.now();

Token token = Token.builder()
    .token(tokenString)
    .createdAt(now)
    .expiresAt(now.plusHours(24))
    .user(user)
    .build();

tokenRepository.save(token);
```

**Validating a Token:**

```java
Optional<Token> optionalToken = tokenRepository.findByToken(tokenString);
if (optionalToken.isPresent()) {
    Token token = optionalToken.get();
    if (token.getExpiresAt().isAfter(LocalDateTime.now()) && token.getValidatedAt() == null) {
        token.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(token);
        // Proceed with authentication or other logic
    } else {
        // Token is expired or already validated
    }
} else {
    // Token does not exist
}
```

---

## 5. **User Entity**

### **Code Overview**

```java
package com.wchamara.book.user;

import com.wchamara.book.role.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_users")
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails, Principal {


    @Id
    @GeneratedValue
    private Integer id;

    private String firstname;
    private String lastname;
    private LocalDateTime dateOfBirth;

    @Column(unique = true)
    private String email;

    private String password;
    private Boolean accountLocked;
    private Boolean enabled;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDateTime;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    /**
     * Returns the name of this {@code Principal}.
     *
     * @return the name of this {@code Principal}.
     */
    @Override
    public String getName() {
        return email;
    }

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the username used to authenticate the user. Cannot return
     * <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }
}
```

### **Explanation**

- **Lombok Annotations**:
  - **`@Getter` & `@Setter`**: Generate getters and setters.
  - **`@Builder`**: Implements the builder pattern.
  - **`@AllArgsConstructor` & `@NoArgsConstructor`**: Generate all-argument and no-argument constructors.

- **JPA Annotations**:
  - **`@Entity`**: Marks the class as a JPA entity.
  - **`@Table(name = "_users")`**: Specifies the table name as `_users` to avoid conflicts with reserved keywords like `USER` in some databases.
  - **`@Id` & `@GeneratedValue`**: Define the primary key and its generation strategy.
  - **`@Column(unique = true)`**: Ensures the `email` field is unique.
  - **`@ManyToMany(fetch = FetchType.EAGER)`**: Establishes a many-to-many relationship with `Role`. The `EAGER` fetch type ensures that roles are loaded immediately with the user, which is essential for authentication.
  - **`@EntityListeners(AuditingEntityListener.class)`**: Enables auditing for fields like `createdDate` and `lastModifiedDateTime`.

- **Auditing**:
  - **`@CreatedDate`**: Automatically sets the `createdDate` when the user is first persisted.
  - **`@LastModifiedDate`**: Updates the `lastModifiedDateTime` whenever the user entity is updated.

- **Spring Security Interfaces**:
  - **`UserDetails`**: Provides core user information to Spring Security.
  - **`Principal`**: Represents the currently authenticated user.

- **Methods Implemented from `UserDetails` and `Principal`**:
  - **`getName()`**: Returns the user's email as the principal name.
  - **`getAuthorities()`**: Translates the user's roles into Spring Security's `GrantedAuthority` objects.
  - **`getPassword()` & `getUsername()`**: Return the user's password and email, respectively.
  - **`isAccountNonExpired()`, `isAccountNonLocked()`, `isCredentialsNonExpired()`**: These methods currently return `false`, which implies:
    - **`isAccountNonExpired()`**: The account is expired.
    - **`isAccountNonLocked()`**: The account is locked.
    - **`isCredentialsNonExpired()`**: The credentials are expired.
    
    **Note**: Returning `false` for these methods will prevent the user from authenticating successfully. Typically, these should be dynamically determined based on user status.

  - **`isEnabled()`**: Returns the `enabled` field, indicating whether the user is active.

- **Custom Method**:
  - **`getFullName()`**: Concatenates the `firstname` and `lastname` to provide the user's full name.

### **Potential Issues and Recommendations**

1. **Authentication Blocked**:
   - **Issue**: The methods `isAccountNonExpired()`, `isAccountNonLocked()`, and `isCredentialsNonExpired()` all return `false`.
   - **Consequence**: Users will not be able to authenticate because Spring Security checks these flags and requires them to be `true` for successful authentication.
   - **Recommendation**: Modify these methods to reflect the actual status of the user. For example:

     ```java
     @Override
     public boolean isAccountNonExpired() {
         // Implement logic to determine if the account is non-expired
         return true; // or based on a field
     }

     @Override
     public boolean isAccountNonLocked() {
         // Implement logic to determine if the account is non-locked
         return !accountLocked;
     }

     @Override
     public boolean isCredentialsNonExpired() {
         // Implement logic to determine if credentials are non-expired
         return true; // or based on a field
     }
     ```

2. **Password Handling**:
   - **Issue**: The `password` field is stored as plain text.
   - **Recommendation**: Always store passwords in an encrypted or hashed form. Use Spring Security’s `PasswordEncoder` to hash passwords before saving them to the database.

3. **EAGER Fetching**:
   - **Consideration**: Using `FetchType.EAGER` for roles means that every time a user is fetched, all associated roles are also fetched. While this is necessary for authentication, be cautious of performance implications if the number of roles is large.

### **Example Usage**

**Creating a New User:**

```java
Role userRole = roleRepository.findByName("USER").orElseThrow();

User newUser = User.builder()
    .firstname("John")
    .lastname("Doe")
    .dateOfBirth(LocalDateTime.of(1990, 1, 1, 0, 0))
    .email("john.doe@example.com")
    .password(passwordEncoder.encode("securePassword"))
    .accountLocked(false)
    .enabled(true)
    .roles(List.of(userRole))
    .build();

userRepository.save(newUser);
```

**Authenticating a User:**

When a user attempts to log in, Spring Security uses the `UserDetailsService` to load the user by email, then checks the credentials and the flags (`isAccountNonLocked`, `isEnabled`, etc.) to determine if authentication should succeed.

---

## 6. **Interconnections and Application Flow**

### **Entity Relationships**

1. **User ↔ Role (Many-to-Many)**:
   - A user can have multiple roles (e.g., USER, ADMIN).
   - A role can be assigned to multiple users.
   - Managed via a join table (automatically handled by JPA unless specified otherwise).

2. **User ↔ Token (One-to-Many)**:
   - A user can have multiple tokens (e.g., for password resets, email verification).
   - Each token is associated with one user.

### **Application Flow Example**

**Scenario**: A user registers, gets assigned a role, and receives a token for email verification.

1. **Registration**:
   - User submits registration details (firstname, lastname, email, password).
   - Password is hashed using `PasswordEncoder`.
   - A new `User` entity is created with `enabled = false`.
   - The "USER" role is assigned to the user.
   - The user is saved to the database.

2. **Email Verification Token Generation**:
   - A `Token` is generated with a unique token string, `createdAt`, `expiresAt`, and associated with the user.
   - The token is saved to the database.
   - An email is sent to the user containing the verification link with the token.

3. **Email Verification**:
   - User clicks the verification link.
   - The application retrieves the `Token` by the token string.
   - Checks if the token is valid (not expired, not already validated).
   - Sets `validatedAt` to the current time.
   - Sets `enabled = true` on the user.
   - Saves the updated user and token.

4. **Authentication**:
   - User logs in with email and password.
   - Spring Security's `UserDetailsService` loads the user.
   - Checks credentials and flags (`isAccountNonLocked`, `isEnabled`, etc.).
   - If valid, authentication succeeds, and the user gains access based on their roles.

### **Code Integration Example**

**User Registration Service:**

```java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserRegistrationDto registrationDto) {
        // Check if email already exists
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Encode the password
        String encodedPassword = passwordEncoder.encode(registrationDto.getPassword());

        // Assign the USER role
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));

        // Create the user
        User user = User.builder()
                .firstname(registrationDto.getFirstname())
                .lastname(registrationDto.getLastname())
                .dateOfBirth(registrationDto.getDateOfBirth())
                .email(registrationDto.getEmail())
                .password(encodedPassword)
                .accountLocked(false)
                .enabled(false) // Initially disabled until email verification
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);

        // Generate email verification token
        String tokenString = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        Token token = Token.builder()
                .token(tokenString)
                .createdAt(now)
                .expiresAt(now.plusHours(24))
                .user(user)
                .build();

        tokenRepository.save(token);

        // Send verification email (pseudo-code)
        emailService.sendVerificationEmail(user.getEmail(), tokenString);
    }
}
```

**Email Verification Controller:**

```java
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String tokenString) {
        Token token = tokenRepository.findByToken(tokenString)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().body("Token expired");
        }

        if (token.getValidatedAt() != null) {
            return ResponseEntity.badRequest().body("Token already used");
        }

        // Validate the token
        token.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(token);

        // Enable the user
        User user = token.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        return ResponseEntity.ok("Email verified successfully");
    }
}
```

---

## 7. **Security Configuration**

To integrate the `User` entity with Spring Security, you need to configure security settings. Here's a basic example:

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService; // Custom UserDetailsService

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // For simplicity; enable in production
            .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .httpBasic(); // Or formLogin, JWT, etc.
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

**Custom `UserDetailsService` Implementation:**

```java
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                      .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return user; // Since User implements UserDetails
    }
}
```

---

## 8. **Database Schema Representation**

Based on the entities, here's how the database schema might look:

1. **Table: `_users`**
   - **id**: Integer (Primary Key)
   - **firstname**: String
   - **lastname**: String
   - **date_of_birth**: Timestamp
   - **email**: String (Unique)
   - **password**: String
   - **account_locked**: Boolean
   - **enabled**: Boolean
   - **created_date**: Timestamp
   - **last_modified_date_time**: Timestamp

2. **Table: `role`**
   - **id**: Integer (Primary Key)
   - **name**: String (Unique)
   - **created_date**: Timestamp
   - **last_modified_date_time**: Timestamp

3. **Table: `token`**
   - **id**: Integer (Primary Key)
   - **token**: String
   - **created_at**: Timestamp
   - **expires_at**: Timestamp
   - **validated_at**: Timestamp
   - **user_id**: Integer (Foreign Key referencing `_users.id`)

4. **Join Table: `user_roles`** (Automatically managed by JPA for the Many-to-Many relationship)
   - **user_id**: Integer (Foreign Key referencing `_users.id`)
   - **role_id**: Integer (Foreign Key referencing `role.id`)

---

## 9. **Conclusion and Best Practices**

The provided code snippets form a solid foundation for a user management system with role-based access control and token-based operations (like email verification or password resets). Here are some best practices and recommendations to enhance the system:

1. **Password Security**:
   - Always store hashed passwords using a strong algorithm like BCrypt.
   - Implement password strength validation during registration.

2. **User Status Flags**:
   - Ensure that `isAccountNonExpired()`, `isAccountNonLocked()`, and `isCredentialsNonExpired()` methods accurately reflect the user's status to facilitate proper authentication behavior.

3. **Token Security**:
   - Use secure, random token generation methods.
   - Implement token expiration and invalidation mechanisms to prevent misuse.

4. **Role Management**:
   - Define roles clearly and manage them via an admin interface or secure backend processes.
   - Consider using hierarchical roles if the application requires complex access control.

5. **Exception Handling**:
   - Implement comprehensive exception handling to manage scenarios like duplicate emails, invalid tokens, etc., providing meaningful feedback to the client.

6. **Logging and Monitoring**:
   - Integrate logging to track important events like user registrations, logins, and token validations.
   - Monitor authentication attempts to detect and prevent potential security threats.

7. **API Documentation**:
   - Use tools like Swagger/OpenAPI to document the API endpoints, making it easier for frontend developers and third parties to integrate.

8. **Testing**:
   - Write unit and integration tests to ensure that the authentication flow, role assignments, and token validations work as expected.

By adhering to these practices and continually refining the system based on requirements and feedback, you can build a robust and secure user management system within your Spring Boot application.

# 📗Repository Layer for Role, Token and User Entities


```java
package com.wchamara.book.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String role);
}

```

```java
package com.wchamara.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer>{

    Optional<Token> findByToken(String token);
}

```

```java
package com.wchamara.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}

```

Certainly! Let's delve deeply into the three repository interfaces you've provided: `RoleRepository`, `TokenRepository`, and `UserRepository`. These repositories are fundamental components in your Spring Boot application's data access layer, leveraging Spring Data JPA to interact seamlessly with the underlying database. We'll explore each repository's structure, functionality, and practical usage with detailed examples.

---

## Table of Contents

1. [Understanding Spring Data JPA Repositories](#1-understanding-spring-data-jpa-repositories)
2. [RoleRepository](#2-rolerepository)
    - [Code Overview](#code-overview)
    - [Detailed Explanation](#detailed-explanation)
    - [Example Usage](#example-usage)
3. [TokenRepository](#3-tokenrepository)
    - [Code Overview](#code-overview-1)
    - [Detailed Explanation](#detailed-explanation-1)
    - [Example Usage](#example-usage-1)
4. [UserRepository](#4-userrepository)
    - [Code Overview](#code-overview-2)
    - [Detailed Explanation](#detailed-explanation-2)
    - [Example Usage](#example-usage-2)
5. [Integration with Services and Controllers](#5-integration-with-services-and-controllers)
    - [User Registration Flow](#user-registration-flow)
    - [Email Verification Flow](#email-verification-flow)
6. [Best Practices and Advanced Features](#6-best-practices-and-advanced-features)
    - [Custom Query Methods](#custom-query-methods)
    - [Handling Optional Results](#handling-optional-results)
    - [Transactions](#transactions)
    - [Pagination and Sorting](#pagination-and-sorting)
    - [Custom Queries with JPQL or Native SQL](#custom-queries-with-jpql-or-native-sql)
    - [Projections and DTOs](#projections-and-dtos)
    - [Indexing and Performance Optimization](#indexing-and-performance-optimization)
    - [Exception Handling](#exception-handling)
    - [Unit and Integration Testing](#unit-and-integration-testing)
7. [Conclusion](#7-conclusion)

---

## 1. Understanding Spring Data JPA Repositories

### What is Spring Data JPA?

Spring Data JPA is a part of the larger Spring Data family, aimed at simplifying data access and persistence in Spring applications. It provides a repository abstraction over the data access layer, eliminating much of the boilerplate code required to interact with databases.

### What is `JpaRepository`?

`JpaRepository` is a JPA-specific extension of the `Repository` interface in Spring Data. It provides a comprehensive set of CRUD (Create, Read, Update, Delete) operations and additional JPA-related functionality. By extending `JpaRepository`, your repository interfaces inherit these methods without needing to implement them manually.

**Key Features of `JpaRepository`:**

- **CRUD Operations**: Methods like `save()`, `findById()`, `findAll()`, `delete()`, etc.
- **Paging and Sorting**: Methods that support pagination and sorting, such as `findAll(Pageable pageable)`.
- **Custom Query Methods**: Ability to define query methods based on method naming conventions.
- **JPA Annotations Support**: Support for JPA-specific annotations and features.

### Benefits of Using Spring Data JPA Repositories

- **Reduced Boilerplate Code**: No need to implement common data access methods.
- **Consistency**: Standardized approach to data access across the application.
- **Customization**: Ability to define custom queries using method names or JPQL/SQL.
- **Integration**: Seamless integration with Spring's dependency injection and other Spring features.

---

## 2. RoleRepository

### Code Overview

```java
package com.wchamara.book.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String role);
}
```

### Detailed Explanation

- **Interface Definition**: `RoleRepository` is an interface that extends `JpaRepository<Role, Integer>`.
  
  - **`Role`**: The entity type that this repository manages.
  - **`Integer`**: The type of the primary key (ID) of the `Role` entity.

- **Inherited Methods**: By extending `JpaRepository`, `RoleRepository` inherits a suite of methods for performing CRUD operations, such as:
  
  - **`save(Role entity)`**: Save a role to the database.
  - **`findById(Integer id)`**: Find a role by its ID.
  - **`findAll()`**: Retrieve all roles.
  - **`delete(Role entity)`**: Delete a role.

- **Custom Method**: `Optional<Role> findByName(String role)`

  - **Purpose**: Retrieve a `Role` entity based on its `name` field.
  - **Return Type**: `Optional<Role>` ensures that the method handles the case where a role with the specified name might not exist, preventing `NullPointerException`.
  - **Method Naming Convention**: Spring Data JPA interprets `findByName` as a query that selects roles where the `name` field matches the provided parameter.

### Example Usage

Let's explore how to use `RoleRepository` in a service class to manage roles.

#### Role Service Example

```java
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Creates a new role if it does not already exist.
     */
    public Role createRole(String roleName) {
        // Check if the role already exists
        Optional<Role> existingRole = roleRepository.findByName(roleName);
        if (existingRole.isPresent()) {
            throw new IllegalArgumentException("Role already exists");
        }

        // Create and save the new role
        Role role = Role.builder()
                .name(roleName)
                .build();
        return roleRepository.save(role);
    }

    /**
     * Retrieves a role by name.
     */
    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    /**
     * Retrieves all roles.
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
```

#### Usage in a Controller

```java
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody RoleDto roleDto) {
        Role createdRole = roleService.createRole(roleDto.getName());
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) {
        Role role = roleService.getRoleByName(name);
        return ResponseEntity.ok(role);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }
}
```

#### Explanation of Example Usage

1. **Creating a Role**:
   - **Endpoint**: `POST /api/roles`
   - **Request Body**: JSON containing the role name.
   - **Process**:
     - The controller calls `roleService.createRole()` with the provided role name.
     - `RoleService` checks if a role with that name already exists using `findByName()`.
     - If not, it creates a new `Role` entity and saves it using `save()`.

2. **Retrieving a Role by Name**:
   - **Endpoint**: `GET /api/roles/{name}`
   - **Path Variable**: `name` - the name of the role.
   - **Process**:
     - The controller calls `roleService.getRoleByName()` with the role name.
     - `RoleService` retrieves the role using `findByName()`.
     - If the role exists, it is returned; otherwise, an exception is thrown.

3. **Retrieving All Roles**:
   - **Endpoint**: `GET /api/roles`
   - **Process**:
     - The controller calls `roleService.getAllRoles()`.
     - `RoleService` uses `findAll()` to retrieve all roles from the database.

### Practical Example

Suppose you want to assign a role to a user during registration.

```java
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(UserRegistrationDto registrationDto) {
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Fetch the 'USER' role
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));

        // Create new user
        User user = User.builder()
                .firstname(registrationDto.getFirstname())
                .lastname(registrationDto.getLastname())
                .dateOfBirth(registrationDto.getDateOfBirth())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .accountLocked(false)
                .enabled(false) // Initially disabled until email verification
                .roles(List.of(userRole))
                .build();

        return userRepository.save(user);
    }
}
```

**Explanation:**

- The `UserService` class handles user registration.
- It uses `RoleRepository` to fetch the "USER" role.
- The role is then assigned to the new user by setting the `roles` list.
- Finally, the user is saved to the database.

---

## 3. TokenRepository

### Code Overview

```java
package com.wchamara.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer>{

    Optional<Token> findByToken(String token);
}
```

### Detailed Explanation

- **Interface Definition**: `TokenRepository` extends `JpaRepository<Token, Integer>`.
  
  - **`Token`**: The entity type managed by this repository.
  - **`Integer`**: The type of the primary key of the `Token` entity.

- **Inherited Methods**: Similar to `RoleRepository`, `TokenRepository` inherits CRUD and other standard methods.

- **Custom Method**: `Optional<Token> findByToken(String token)`

  - **Purpose**: Retrieve a `Token` entity based on its `token` field.
  - **Return Type**: `Optional<Token>` handles the case where no token matches the provided string.

### Example Usage

Let's consider scenarios such as token creation (e.g., for email verification or password reset) and token validation.

#### Token Service Example

```java
@Service
public class TokenService {

    private final TokenRepository tokenRepository;
    private final UserRepository userRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new token for the given user.
     */
    public Token createTokenForUser(User user) {
        String tokenString = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        Token token = Token.builder()
                .token(tokenString)
                .createdAt(now)
                .expiresAt(now.plusHours(24))
                .user(user)
                .build();

        return tokenRepository.save(token);
    }

    /**
     * Validates the token and returns the associated user if valid.
     */
    public User validateToken(String tokenString) {
        Token token = tokenRepository.findByToken(tokenString)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        if (token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token has expired");
        }

        if (token.getValidatedAt() != null) {
            throw new IllegalArgumentException("Token has already been used");
        }

        // Mark token as validated
        token.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(token);

        return token.getUser();
    }
}
```

#### Usage in a Registration Flow

1. **User Registration**:
   - After creating a new user, generate a token for email verification.

2. **Email Verification**:
   - User clicks on the verification link containing the token.
   - The system validates the token and activates the user account.

**Example Code Snippets:**

```java
@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       TokenService tokenService, EmailService emailService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenService = tokenService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerNewUser(UserRegistrationDto registrationDto) {
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));

        User user = User.builder()
                .firstname(registrationDto.getFirstname())
                .lastname(registrationDto.getLastname())
                .dateOfBirth(registrationDto.getDateOfBirth())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .accountLocked(false)
                .enabled(false) // Initially disabled until email verification
                .roles(List.of(userRole))
                .build();

        user = userRepository.save(user);

        // Generate and save token
        Token token = tokenService.createTokenForUser(user);

        // Send verification email
        emailService.sendVerificationEmail(user.getEmail(), token.getToken());

        return user;
    }

    public void verifyUser(String tokenString) {
        User user = tokenService.validateToken(tokenString);
        user.setEnabled(true);
        userRepository.save(user);
    }
}
```

#### Explanation of Example Usage

1. **Registration**:
   - A new user is created and saved to the database with `enabled = false`.
   - A token is generated for this user using `TokenService.createTokenForUser()`.
   - An email is sent to the user containing the verification token.

2. **Verification**:
   - When the user clicks the verification link, the token is validated using `TokenService.validateToken()`.
   - If valid, the user's `enabled` field is set to `true`, allowing them to authenticate.

---

## 4. UserRepository

### Code Overview

```java
package com.wchamara.book.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
```

### Detailed Explanation

- **Interface Definition**: `UserRepository` extends `JpaRepository<User, Integer>`.
  
  - **`User`**: The entity type managed by this repository.
  - **`Integer`**: The type of the primary key of the `User` entity.

- **Inherited Methods**: Provides standard CRUD operations via `JpaRepository`.

- **Custom Method**: `Optional<User> findByEmail(String email)`

  - **Purpose**: Retrieve a `User` entity based on its `email` field.
  - **Return Type**: `Optional<User>` handles the case where a user with the specified email may not exist.

### Example Usage

Let's explore how to use `UserRepository` in various scenarios such as user registration, authentication, and profile management.

#### User Service Example

```java
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       TokenService tokenService, EmailService emailService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenService = tokenService;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user and sends an email verification token.
     */
    public User registerNewUser(UserRegistrationDto registrationDto) {
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));

        User user = User.builder()
                .firstname(registrationDto.getFirstname())
                .lastname(registrationDto.getLastname())
                .dateOfBirth(registrationDto.getDateOfBirth())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        user = userRepository.save(user);

        // Generate and save token
        Token token = tokenService.createTokenForUser(user);

        // Send verification email
        emailService.sendVerificationEmail(user.getEmail(), token.getToken());

        return user;
    }

    /**
     * Loads a user by username (email) for Spring Security authentication.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    /**
     * Updates user profile.
     */
    public User updateUserProfile(Integer userId, UserProfileDto profileDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstname(profileDto.getFirstname());
        user.setLastname(profileDto.getLastname());
        user.setDateOfBirth(profileDto.getDateOfBirth());

        // Optionally update email or password
        if (profileDto.getEmail() != null && !profileDto.getEmail().equals(user.getEmail())) {
            if (userRepository.findByEmail(profileDto.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email already in use");
            }
            user.setEmail(profileDto.getEmail());
            user.setEnabled(false); // Require re-verification
            Token token = tokenService.createTokenForUser(user);
            emailService.sendVerificationEmail(user.getEmail(), token.getToken());
        }

        if (profileDto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(profileDto.getPassword()));
        }

        return userRepository.save(user);
    }

    /**
     * Retrieves user by ID.
     */
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
```

#### Explanation of Example Usage

1. **Registering a New User**:
   - **Email Uniqueness Check**: `findByEmail()` is used to check if a user with the given email already exists.
   - **Role Assignment**: The "USER" role is fetched and assigned to the new user.
   - **Password Encryption**: The user's password is encoded using `PasswordEncoder`.
   - **Saving User**: The new user is saved to the database using `userRepository.save()`.
   - **Token Generation and Email Sending**: A token is created for email verification, and an email is sent to the user with the token.

2. **Loading User for Authentication**:
   - **Implementation of `UserDetailsService`**: The `UserService` implements `UserDetailsService` to integrate with Spring Security.
   - **`loadUserByUsername` Method**: Uses `findByEmail()` to retrieve the user based on the provided email. If not found, throws `UsernameNotFoundException`.

3. **Updating User Profile**:
   - **Fetching User by ID**: Uses `findById()` to retrieve the user to update.
   - **Updating Fields**: Updates fields like firstname, lastname, dateOfBirth, and optionally email and password.
   - **Email Update**: If the email is updated, checks for uniqueness using `findByEmail()`, disables the user, creates a new token, and sends a verification email.
   - **Password Update**: If the password is provided, it is encoded before saving.
   - **Saving Changes**: Updates are saved using `userRepository.save()`.

4. **Retrieving a User by ID**:
   - **`getUserById` Method**: Fetches a user by their primary key using `findById()`.

### Practical Example

**Authentication Flow with Spring Security:**

1. **User Attempts to Login**:
   - The user provides their email and password.
   
2. **Spring Security Calls `loadUserByUsername`**:
   - Spring Security uses the `UserService.loadUserByUsername()` method to load the user details.
   - `UserRepository.findByEmail(email)` retrieves the user entity.
   - If the user is found, their details (including roles) are returned to Spring Security.
   - If the user is not found, an exception is thrown, and authentication fails.

3. **Spring Security Validates Credentials**:
   - Spring Security compares the provided password with the encoded password from the user entity using `PasswordEncoder`.
   - It also checks other flags like `isAccountNonLocked`, `isEnabled`, etc., as defined in the `User` entity.

4. **Authentication Success or Failure**:
   - If all checks pass, the user is authenticated and granted access based on their roles.
   - If any check fails, authentication is denied.

**Example Code in Security Configuration:**

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Enable CSRF protection in production
            .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll() // Allow public access to auth endpoints
                .anyRequest().authenticated() // Secure all other endpoints
            .and()
            .formLogin(); // Enable form-based login
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

**Explanation:**

- **`configure(AuthenticationManagerBuilder auth)`**:
  - Configures Spring Security to use `UserService` as the `UserDetailsService`.
  - Specifies the `PasswordEncoder` to use for password matching.

- **`configure(HttpSecurity http)`**:
  - Disables CSRF protection for simplicity (not recommended in production).
  - Permits all requests to `/api/auth/**` (e.g., registration, login).
  - Requires authentication for all other requests.
  - Enables form-based login.

---

## 5. Integration with Services and Controllers

To fully understand how these repositories work together, let's walk through a typical user registration and email verification flow.

### User Registration Flow

1. **User Submits Registration Details**:
   - Endpoint: `POST /api/auth/register`
   - Request Body: JSON containing `firstname`, `lastname`, `email`, `password`, etc.

2. **Controller Receives Request**:
   - The controller delegates the registration process to the `UserService`.

3. **UserService Processes Registration**:
   - **Email Uniqueness Check**: Uses `UserRepository.findByEmail()` to ensure the email isn't already registered.
   - **Role Assignment**: Retrieves the "USER" role using `RoleRepository.findByName()`.
   - **Password Encryption**: Encodes the user's password.
   - **User Creation**: Constructs a new `User` entity and saves it using `UserRepository.save()`.
   - **Token Generation**: Creates a verification token using `TokenService.createTokenForUser()`.
   - **Email Sending**: Sends a verification email containing the token using `EmailService.sendVerificationEmail()`.

4. **Response Sent to Client**:
   - The controller responds with a success message indicating that registration was successful and that a verification email has been sent.

### Email Verification Flow

1. **User Clicks Verification Link**:
   - The link contains the token, e.g., `GET /api/auth/verify?token=abcdefg`.

2. **Controller Receives Verification Request**:
   - The controller extracts the token from the request parameters and delegates verification to the `UserService`.

3. **UserService Validates Token**:
   - **Token Retrieval**: Uses `TokenRepository.findByToken()` to fetch the token.
   - **Token Validation**:
     - Checks if the token exists.
     - Verifies that the token hasn't expired.
     - Ensures the token hasn't already been used.
   - **User Activation**: Sets the user's `enabled` field to `true` using `UserRepository.save()`.
   - **Token Update**: Marks the token as validated by setting `validatedAt`.

4. **Response Sent to Client**:
   - The controller responds with a success message indicating that the email has been verified and the account is now active.

### Example Controller for Authentication

```java
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public AuthController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto registrationDto) {
        userService.registerNewUser(registrationDto);
        return new ResponseEntity<>("User registered successfully. Please check your email for verification.", HttpStatus.CREATED);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("token") String token) {
        userService.verifyUser(token);
        return ResponseEntity.ok("Email verified successfully. You can now log in.");
    }

    // Additional endpoints like login can be added here
}
```

**Explanation:**

- **`registerUser` Endpoint**:
  - Receives user registration details.
  - Calls `userService.registerNewUser()` to handle the registration logic.
  - Responds with a success message.

- **`verifyUser` Endpoint**:
  - Receives the verification token as a query parameter.
  - Calls `userService.verifyUser()` to handle the verification logic.
  - Responds with a success message upon successful verification.

---

## 6. Best Practices and Advanced Features

### Custom Query Methods

Spring Data JPA allows the definition of custom query methods based on method naming conventions. This feature eliminates the need for writing boilerplate JPQL or SQL queries.

**Example:**

```java
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    List<User> findByFirstname(String firstname);
    List<User> findByLastnameAndEmail(String lastname, String email);
}
```

Spring Data JPA interprets these method names and automatically generates the corresponding queries.

### Handling Optional Results

The use of `Optional<T>` as return types in repository methods encourages handling of absent values gracefully, preventing `NullPointerException`s.

**Example:**

```java
Optional<User> optionalUser = userRepository.findByEmail(email);
if (optionalUser.isPresent()) {
    User user = optionalUser.get();
    // Proceed with user
} else {
    // Handle user not found
}
```

Alternatively, you can use `orElseThrow` for concise exception handling:

```java
User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
```

### Transactions

Spring Data JPA methods are transactional by default. However, for complex operations that span multiple repository calls or require atomicity, you should explicitly define transactions.

**Example:**

```java
@Service
public class UserService {

    // Inject repositories and other dependencies

    @Transactional
    public void performComplexOperation() {
        // Multiple repository operations
        // All operations succeed or fail together
    }
}
```

### Pagination and Sorting

Repositories extending `JpaRepository` can utilize methods that support pagination and sorting.

**Example:**

```java
public Page<User> getUsers(int page, int size, String sortBy) {
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return userRepository.findAll(pageable);
}
```

**Usage in Controller:**

```java
@GetMapping("/users")
public ResponseEntity<Page<User>> getUsers(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
    Page<User> users = userService.getUsers(page, size, sortBy);
    return ResponseEntity.ok(users);
}
```

### Custom Queries with JPQL or Native SQL

In cases where method naming conventions are insufficient, you can define custom queries using the `@Query` annotation.

**Example:**

```java
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email LIKE %:domain")
    List<User> findByEmailDomain(@Param("domain") String domain);
}
```

**Usage:**

```java
List<User> usersWithGmail = userRepository.findByEmailDomain("gmail.com");
```

### Projections and DTOs

To optimize performance and data transfer, use projections or Data Transfer Objects (DTOs) to fetch only necessary fields.

**Example:**

```java
public interface UserSummary {
    String getFirstname();
    String getLastname();
    String getEmail();
}

public interface UserRepository extends JpaRepository<User, Integer> {
    List<UserSummary> findAllProjectedBy();
}
```

**Usage:**

```java
List<UserSummary> summaries = userRepository.findAllProjectedBy();
summaries.forEach(summary -> {
    System.out.println(summary.getFirstname() + " " + summary.getLastname() + " - " + summary.getEmail());
});
```

### Indexing and Performance Optimization

Ensure that frequently queried fields (e.g., `email` in `User`, `name` in `Role`) are indexed in the database to improve query performance. This is already partially handled by JPA annotations like `@Column(unique = true)`, which often implies an index.

**Example:**

In the `Role` entity:

```java
@Column(unique = true, nullable = false)
private String name;
```

This typically creates a unique index on the `name` column.

### Exception Handling

Implement robust exception handling strategies to manage scenarios like entity not found, data integrity violations, etc.

**Example:**

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFound(UsernameNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgs(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Handle other exceptions
}
```

### Unit and Integration Testing

Test your repositories and services to ensure they function correctly.

**Example:**

**Repository Test Using H2 In-Memory Database:**

```java
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        User user = User.builder()
                .firstname("Jane")
                .lastname("Doe")
                .email("jane.doe@example.com")
                .password("password")
                .enabled(true)
                .build();
        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail("jane.doe@example.com");
        assertTrue(foundUser.isPresent());
        assertEquals("Jane", foundUser.get().getFirstname());
    }
}
```

**Explanation:**

- **`@DataJpaTest`**: Configures an in-memory database, scans for `@Entity` classes, and configures Spring Data JPA repositories.
- **Test Method**: Creates and saves a `User` entity, then retrieves it using `findByEmail()` to verify functionality.

---

## 7. Conclusion

The repository interfaces `RoleRepository`, `TokenRepository`, and `UserRepository` are integral components of your Spring Boot application's data access layer. They leverage Spring Data JPA’s capabilities to provide robust, efficient, and easy-to-use mechanisms for interacting with the database.

**Key Takeaways:**

- **Leverage `JpaRepository`**: Extending `JpaRepository` provides a wealth of functionality out-of-the-box, reducing the need for boilerplate code.
- **Use Method Naming Conventions**: Define custom query methods by following Spring Data JPA’s naming conventions, allowing for automatic query generation.
- **Handle `Optional` Properly**: Use `Optional<T>` to handle potential `null` values gracefully.
- **Integrate with Services and Controllers**: Repositories are typically used within service classes, which encapsulate business logic and are called by controllers handling HTTP requests.
- **Follow Best Practices**: Implement proper exception handling, security measures, transaction management, and testing to ensure a robust application.

By understanding and effectively utilizing Spring Data JPA repositories, you can build scalable and maintainable data access layers in your Spring Boot applications.

---

Feel free to ask if you need further clarification or more examples on any of these topics!

## 📗register endpoint

made all the depedencies the same version

```xml
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.6</version>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.6</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.6</version>
            <scope>runtime</scope>
        </dependency>
```


```java
package com.wchamara.book.auth;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) {

        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }


}

```

```java
package com.wchamara.book.auth;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public void register(RegistrationRequest request) {

    }
}

```

```java
package com.wchamara.book.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "First name is required")
    @NotBlank(message = "First name cannot be empty")
    private String firstname;

    @NotEmpty(message = "Last name is required")
    @NotBlank(message = "Last name cannot be empty")
    private String lastname;

    @NotEmpty(message = "Email is required")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

}

```

```java
package com.wchamara.book.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(
                                        "/auth/**",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html"
                                )
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}

```

```java
package com.wchamara.book.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {


        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        final String userEmail;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            userEmail = jwtService.extractUsername(jwt);
            if (userEmail != null && !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}

```

```java
package com.wchamara.book.security;

import com.wchamara.book.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may be case-sensitive, or case-insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested.
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

```

```java
package com.wchamara.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

```

```java
package com.wchamara.book.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.expiration}")
    private Long jwtExpirationInMs;

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);


    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSignedKey()).build().parseSignedClaims(token).getPayload();
    }

    private String generateToken(Map<String, Object> claims, UserDetails userDetails) {

        return buildToke(claims, userDetails, jwtExpirationInMs);
    }

    private String buildToke(Map<String, Object> extraClaims, UserDetails userDetails, Long jwtExpirationInMs) {
        var authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .claim("authorities", authorities)
                .signWith(getSignedKey())
                .compact();

    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private SecretKey getSignedKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

```

```yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/book_social_network
    username: username
    password: password
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
  mail:
    host: localhost
    port: 1025
    username: username
    password: password
    properties:
      mail:
        smtp:
          trust: "*"
        auth: true
        starttls:
          enable: true
        connectiontimeout: 5000
        timeout: 3000
        writetimeout: 5000
application:
  security:
    jwt:
      secret-key: secret
      expiration: 86400000
      token-prefix: Bearer
      header-string: Authorization
```

Certainly! Let's conduct an in-depth analysis of the provided Spring Boot application code snippets. These snippets form a comprehensive authentication system using JWT (JSON Web Tokens), Spring Security, and JJWT (Java JWT) library for handling JWT operations. We'll break down each component, understand their roles, and illustrate their interactions with practical examples.

---

## **Table of Contents**

1. [Project Dependencies](#1-project-dependencies)
2. [Authentication Controller](#2-authentication-controller)
3. [Authentication Service](#3-authentication-service)
4. [Registration Request DTO](#4-registration-request-dto)
5. [Security Configuration](#5-security-configuration)
6. [JWT Filter](#6-jwt-filter)
7. [User Details Service Implementation](#7-user-details-service-implementation)
8. [Beans Configuration](#8-beans-configuration)
9. [JWT Service](#9-jwt-service)
10. [Application Configuration (`application.yml`)](#10-application-configuration-applicationyml)
11. [Integration and Workflow](#11-integration-and-workflow)
12. [Best Practices and Recommendations](#12-best-practices-and-recommendations)
13. [Conclusion](#13-conclusion)

---

## 1. **Project Dependencies**

### **Code Overview**

```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.6</version>
</dependency>

<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
    <version>0.12.6</version>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
    <version>0.12.6</version>
    <scope>runtime</scope>
</dependency>
```

### **Explanation**

These dependencies are crucial for handling JWT operations in your Spring Boot application. Here's a breakdown of each:

1. **`jjwt-api`**:
   - **Purpose**: Contains the core API for JWT creation and parsing.
   - **Usage**: Provides interfaces and classes to build and verify JWTs.

2. **`jjwt-impl`**:
   - **Purpose**: Implements the APIs defined in `jjwt-api`.
   - **Usage**: Necessary for the runtime behavior of JWT operations.

3. **`jjwt-jackson`**:
   - **Purpose**: Provides JSON processing using Jackson.
   - **Usage**: Facilitates the serialization and deserialization of JWTs to and from JSON.

### **Version Alignment**

- **Consistency**: All three JJWT dependencies are set to the same version (`0.12.6`), ensuring compatibility and preventing class/method resolution issues.
- **Scope**: `jjwt-jackson` is marked with `<scope>runtime</scope>`, meaning it's only needed during runtime and not at compile-time.

### **Additional Dependencies to Consider**

While not shown, ensure your `pom.xml` includes necessary dependencies for Spring Boot, Spring Security, Lombok, PostgreSQL, and other libraries used in your project.

**Example:**

```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Spring Boot Starter Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- PostgreSQL Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.2.23</version>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.20</version>
        <scope>provided</scope>
    </dependency>

    <!-- JJWT Dependencies as above -->
</dependencies>
```

---

## 2. **Authentication Controller**

### **Code Overview**

```java
package com.wchamara.book.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) {
        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }

}
```

### **Explanation**

- **Annotations**:
  - **`@RestController`**: Indicates that this class is a REST controller, handling HTTP requests and returning responses.
  - **`@RequestMapping("auth")`**: Sets the base path for all endpoints in this controller to `/auth`.
  - **`@RequiredArgsConstructor`**: Lombok annotation that generates a constructor with required arguments (i.e., for `final` fields).
  - **`@Tag(name = "Authentication")`**: Swagger/OpenAPI annotation for grouping endpoints under the "Authentication" tag in API documentation.

- **Dependencies**:
  - **`private final AuthenticationService authenticationService;`**: Injected via constructor injection (thanks to Lombok's `@RequiredArgsConstructor`), this service handles the business logic for authentication.

- **Endpoints**:
  - **`@PostMapping("/register")`**:
    - **Path**: `/auth/register`
    - **Method**: `POST`
    - **Description**: Handles user registration.
    - **Parameters**:
      - **`@RequestBody @Valid RegistrationRequest request`**:
        - **`@RequestBody`**: Binds the HTTP request body to the `RegistrationRequest` object.
        - **`@Valid`**: Triggers validation based on constraints defined in `RegistrationRequest`.
    - **Response**:
      - **`ResponseEntity<?>`**: Returns an HTTP response.
      - **`HttpStatus.ACCEPTED` (202)**: Indicates that the request has been accepted for processing, but the processing is not yet complete.
      - **`ResponseEntity.accepted().build()`**: Constructs an empty response with status 202.

### **Example Usage**

Assume you have the following JSON payload for registration:

```json
{
    "firstname": "John",
    "lastname": "Doe",
    "email": "john.doe@example.com",
    "password": "SecureP@ssw0rd"
}
```

**HTTP Request:**

```
POST /auth/register
Content-Type: application/json

{
    "firstname": "John",
    "lastname": "Doe",
    "email": "john.doe@example.com",
    "password": "SecureP@ssw0rd"
}
```

**HTTP Response:**

```
HTTP/1.1 202 Accepted
```

**Process Flow:**

1. **Client** sends a `POST` request to `/auth/register` with user details.
2. **AuthenticationController** receives the request and binds the JSON payload to a `RegistrationRequest` object.
3. **Validation**: The `@Valid` annotation ensures that the request data adheres to the constraints defined in `RegistrationRequest`.
4. **AuthenticationService** is invoked to handle the registration logic.
5. **Response**: Returns a 202 Accepted status, indicating that the registration process has been initiated (e.g., sending verification email).

---

## 3. **Authentication Service**

### **Code Overview**

```java
package com.wchamara.book.auth;

import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    public void register(RegistrationRequest request) {

    }
}
```

### **Explanation**

- **`@Service` Annotation**: Marks this class as a Spring service component, making it eligible for component scanning and dependency injection.

- **`register` Method**:
  - **Parameters**: Accepts a `RegistrationRequest` object containing user registration details.
  - **Functionality**: Currently empty, but intended to handle the business logic for user registration.

### **Expected Functionality**

The `register` method should perform the following operations:

1. **Validate Input**: Although validation is triggered by `@Valid` in the controller, additional business validations can be performed here.
2. **Check for Existing User**: Ensure that the email is not already registered.
3. **Encrypt Password**: Hash the user's password before storing it.
4. **Assign Roles**: Assign default roles (e.g., "USER") to the new user.
5. **Save User**: Persist the user entity to the database using `UserRepository`.
6. **Generate JWT Token**: Create a JWT for authentication or verification purposes.
7. **Send Verification Email**: Email the user with a verification link containing the JWT token.

### **Example Implementation**

Let's flesh out the `register` method to perform these tasks.

```java
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenService tokenService;
    private final EmailService emailService;

    public void register(RegistrationRequest request) {
        // 1. Check if user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already in use");
        }

        // 2. Fetch 'USER' role
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("USER role not found"));

        // 3. Create and save user
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(false) // Initially disabled until email verification
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);

        // 4. Generate verification token
        Token verificationToken = tokenService.createTokenForUser(user);

        // 5. Send verification email
        emailService.sendVerificationEmail(user.getEmail(), verificationToken.getToken());
    }
}
```

**Explanation of the Enhanced `register` Method:**

1. **User Existence Check**:
   - **`userRepository.findByEmail(request.getEmail())`**: Checks if a user with the provided email already exists.
   - **Exception Handling**: Throws an `IllegalArgumentException` if the email is already registered.

2. **Role Assignment**:
   - **`roleRepository.findByName("USER")`**: Fetches the "USER" role from the database.
   - **Exception Handling**: Throws a `RuntimeException` if the "USER" role is not found.

3. **User Creation and Saving**:
   - **Builder Pattern**: Utilizes Lombok's `@Builder` to create a new `User` instance.
   - **Password Encryption**: Uses `PasswordEncoder` to hash the user's password before saving.
   - **Enabled Flag**: Sets `enabled` to `false` initially, pending email verification.
   - **Role Assignment**: Assigns the "USER" role to the new user.
   - **`userRepository.save(user)`**: Persists the user to the database.

4. **Verification Token Generation**:
   - **`tokenService.createTokenForUser(user)`**: Generates a JWT token associated with the user for email verification.

5. **Email Sending**:
   - **`emailService.sendVerificationEmail(user.getEmail(), verificationToken.getToken())`**: Sends an email to the user containing the verification token.

### **Practical Example**

Assuming a user submits the following registration details:

```json
{
    "firstname": "Alice",
    "lastname": "Smith",
    "email": "alice.smith@example.com",
    "password": "StrongP@ssw0rd!"
}
```

**Process Flow:**

1. **Controller** receives the `POST /auth/register` request with the above payload.
2. **AuthenticationController** validates the request and delegates to `AuthenticationService.register(request)`.
3. **AuthenticationService**:
   - Checks if `alice.smith@example.com` is already registered.
   - Fetches the "USER" role.
   - Creates a new `User` entity with `enabled = false` and saves it.
   - Generates a verification token associated with Alice.
   - Sends a verification email to Alice with the token.
4. **Client** receives a `202 Accepted` response, indicating that registration is in progress.

---

## 4. **Registration Request DTO**

### **Code Overview**

```java
package com.wchamara.book.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "First name is required")
    @NotBlank(message = "First name cannot be empty")
    private String firstname;

    @NotEmpty(message = "Last name is required")
    @NotBlank(message = "Last name cannot be empty")
    private String lastname;

    @NotEmpty(message = "Email is required")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

}
```

### **Explanation**

- **Annotations**:
  - **`@Getter` & `@Setter`**: Lombok annotations to generate getter and setter methods for all fields.
  - **`@Builder`**: Lombok annotation to implement the Builder pattern, allowing for more readable and maintainable object creation.

- **Validation Constraints**:
  - **`@NotEmpty`**: Ensures that the field is not `null` or empty (i.e., length > 0).
  - **`@NotBlank`**: Ensures that the field is not `null`, empty, or whitespace-only.
  - **`@Email`**: Validates that the field follows a valid email format.
  - **`@Size(min = 8)`**: Ensures that the password is at least 8 characters long.

- **Fields**:
  - **`firstname`**: User's first name.
  - **`lastname`**: User's last name.
  - **`email`**: User's email address.
  - **`password`**: User's password.

### **Example Usage**

**JSON Payload for Registration:**

```json
{
    "firstname": "Bob",
    "lastname": "Johnson",
    "email": "bob.johnson@example.com",
    "password": "P@ssw0rd123"
}
```

**Validation Process:**

1. **Controller** receives the `RegistrationRequest` object via `@RequestBody` and `@Valid`.
2. **Validation Annotations** ensure:
   - `firstname` and `lastname` are not empty or blank.
   - `email` is not empty, not blank, and follows a valid email format.
   - `password` is not empty, not blank, and at least 8 characters long.
3. **Constraint Violations**:
   - If any field fails validation, Spring Boot automatically returns a `400 Bad Request` with details about the validation errors.
   - **Example**: Submitting a password shorter than 8 characters would trigger a validation error.

**Error Response Example:**

```json
{
    "timestamp": "2024-04-27T12:34:56.789+00:00",
    "status": 400,
    "error": "Bad Request",
    "messages": [
        "Password must be at least 8 characters long"
    ],
    "path": "/auth/register"
}
```

---

## 5. **Security Configuration**

### **Code Overview**

```java
package com.wchamara.book.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(
                                        "/auth/**",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui/**",
                                        "/swagger-ui.html"
                                )
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
}
```

### **Explanation**

- **Annotations**:
  - **`@EnableWebSecurity`**: Enables Spring Security’s web security support.
  - **`@Configuration`**: Marks the class as a configuration class.
  - **`@RequiredArgsConstructor`**: Lombok annotation to generate a constructor with required arguments (i.e., for `final` fields).
  - **`@EnableMethodSecurity(securedEnabled = true)`**: Enables method-level security using annotations like `@Secured`.

- **Dependencies**:
  - **`private final AuthenticationProvider authenticationProvider;`**: Injected authentication provider to handle user authentication.
  - **`private final JwtFilter jwtAuthFilter;`**: Injected JWT filter to process JWT tokens in incoming requests.

- **`securityFilterChain` Bean**:
  - **Purpose**: Configures the security filter chain, defining how requests are secured and which filters are applied.
  
  - **Configurations**:
    1. **CORS Configuration**:
       - **`cors(withDefaults())`**: Enables Cross-Origin Resource Sharing with default settings. You can customize it further if needed.
    
    2. **CSRF Configuration**:
       - **`csrf(AbstractHttpConfigurer::disable)`**: Disables CSRF protection. This is common in stateless APIs using JWTs, as CSRF protection is primarily needed for stateful sessions.
       - **Security Note**: Ensure that your application doesn’t have other vulnerabilities that CSRF protection mitigates, especially if it's not entirely stateless.
    
    3. **Authorization Rules**:
       - **`authorizeHttpRequests`**: Defines authorization rules for incoming HTTP requests.
       - **`requestMatchers`**:
         - **Permitted Paths**:
           - **`/auth/**`**: All authentication-related endpoints (e.g., register, login).
           - **Swagger UI Paths**: Enables access to Swagger/OpenAPI documentation without authentication.
         - **`permitAll()`**: Allows unrestricted access to the specified paths.
       - **`anyRequest().authenticated()`**: Requires authentication for all other endpoints not explicitly permitted.
    
    4. **Session Management**:
       - **`sessionManagement(session -> session.sessionCreationPolicy(STATELESS))`**:
         - **`SessionCreationPolicy.STATELESS`**: Disables session creation. The application won't store any session data, relying entirely on JWTs for authentication.
    
    5. **Authentication Provider**:
       - **`authenticationProvider(authenticationProvider)`**: Sets the custom `AuthenticationProvider` for handling authentication logic.
    
    6. **JWT Filter**:
       - **`addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)`**: Adds the `JwtFilter` before the `UsernamePasswordAuthenticationFilter` in the security filter chain. This ensures that JWT processing occurs before Spring Security’s default authentication processing.

### **Example Configuration Flow**

1. **Incoming Request**:
   - **Path**: `/api/books`
   - **Header**: `Authorization: Bearer <JWT_TOKEN>`
   
2. **Security Filter Chain Processing**:
   - **CORS Handling**: Processes CORS headers.
   - **CSRF Disabled**: Skips CSRF checks.
   - **Authorization Rules**:
     - Checks if the request matches any of the permitted paths (`/auth/**`, Swagger paths).
     - Since `/api/books` is not in the permitted paths, it requires authentication.
   
3. **JWT Filter (`JwtFilter`)**:
   - **Extraction**: Extracts the JWT from the `Authorization` header.
   - **Validation**: Validates the JWT and sets the authentication in the `SecurityContext`.
   
4. **Authentication Check**:
   - If JWT is valid, the request proceeds to the secured endpoint (`/api/books`).
   - If JWT is invalid or missing, Spring Security responds with `401 Unauthorized`.

### **Practical Example**

**Scenario**: Accessing a secured endpoint `/api/books` with a valid JWT.

**HTTP Request:**

```
GET /api/books
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwiaWF0IjoxNjEyMzQ1Njc4LCJleHAiOjE2MTIzODIyNzh9.DfGfYbW3nA3bYh5zLkXkHzYh1VqU1ZoQKFtGxVUgkX0
```

**Process Flow:**

1. **CORS Handling**: Processes any CORS preflight requests if necessary.
2. **CSRF Disabled**: Skips CSRF checks.
3. **Authorization Check**:
   - `/api/books` is not in the permitted paths; requires authentication.
4. **JWT Filter**:
   - Extracts the JWT from the `Authorization` header.
   - Validates the JWT using `JwtService`.
   - If valid, sets the authentication in `SecurityContext`.
5. **Endpoint Access**:
   - User is authenticated and authorized to access `/api/books`.
   - Controller handling `/api/books` processes the request and returns data.

---

## 6. **JWT Filter**

### **Code Overview**

```java
package com.wchamara.book.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {


    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {


        if (request.getServletPath().contains("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader(AUTHORIZATION);
        final String jwt;
        final String userEmail;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            userEmail = jwtService.extractUsername(jwt);
            if (userEmail != null && !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
```

### **Explanation**

- **Class and Annotations**:
  - **`@Service`**: Marks this class as a Spring service component.
  - **`@RequiredArgsConstructor`**: Lombok annotation to generate a constructor with required arguments (i.e., for `final` fields).
  - **`JwtFilter`**: Extends `OncePerRequestFilter`, ensuring the filter is invoked once per request.

- **Dependencies**:
  - **`private final JwtService jwtService;`**: Service for handling JWT operations (generation, validation, extraction).
  - **`private final UserDetailsService userDetailsService;`**: Service to load user-specific data.

- **`doFilterInternal` Method**:
  - **Purpose**: Intercepts incoming HTTP requests to extract and validate JWTs, setting the security context accordingly.

- **Filter Logic**:
  1. **Path Exclusion**:
     - **`if (request.getServletPath().contains("/api/v1/auth"))`**:
       - **Purpose**: Excludes authentication-related endpoints from JWT filtering to prevent intercepting login or registration requests.
       - **Action**: Bypasses JWT processing and continues the filter chain.

  2. **Authorization Header Extraction**:
     - **`final String authorizationHeader = request.getHeader(AUTHORIZATION);`**:
       - Extracts the `Authorization` header from the incoming request.
       - **`AUTHORIZATION`** is statically imported from `org.springframework.http.HttpHeaders`.

  3. **JWT Extraction and Validation**:
     - **Check**: Ensures the `Authorization` header starts with `"Bearer "`.
     - **Extraction**: Retrieves the JWT by removing the `"Bearer "` prefix.
     - **Username Extraction**: Uses `jwtService.extractUsername(jwt)` to get the username (typically email) from the JWT claims.
     - **Authentication Check**:
       - **`userEmail != null`**: Ensures that the username was successfully extracted.
       - **`!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()`**: Checks if the user is not already authenticated in the current security context.
     - **User Details Loading**: Loads user details using `userDetailsService.loadUserByUsername(userEmail)`.
     - **Token Validation**: Validates the token against the loaded user details using `jwtService.validateToken(jwt, userDetails)`.
     - **Authentication Token Creation**:
       - **`UsernamePasswordAuthenticationToken`**: Creates an authentication token with user details and authorities.
       - **`setDetails`**: Sets additional authentication details from the request.
     - **Security Context Update**: Sets the authentication token in the `SecurityContextHolder`, marking the user as authenticated.

  4. **Filter Chain Continuation**:
     - **`filterChain.doFilter(request, response);`**: Continues processing the request through the filter chain.

### **Practical Example**

**Scenario**: A user with a valid JWT accesses a secured endpoint `/api/books`.

**HTTP Request:**

```
GET /api/books
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQGV4YW1wbGUuY29tIiwiaWF0IjoxNjEyMzQ1Njc4LCJleHAiOjE2MTIzODIyNzh9.DfGfYbW3nA3bYh5zLkXkHzYh1VqU1ZoQKFtGxVUgkX0
```

**Filter Processing:**

1. **Path Check**:
   - `/api/books` does not contain `/api/v1/auth`; proceeds to JWT processing.

2. **Authorization Header Extraction**:
   - Extracts the JWT token after the `"Bearer "` prefix.

3. **JWT Validation**:
   - **Username Extraction**: Retrieves `user@example.com` from the token.
   - **User Details Loading**: Loads user details for `user@example.com`.
   - **Token Validation**: Ensures the token is valid and not expired.

4. **Authentication Context Setup**:
   - Creates and sets `UsernamePasswordAuthenticationToken` in the `SecurityContextHolder`.

5. **Request Processing**:
   - The request proceeds to the `/api/books` controller with an authenticated user.

**Outcome**:

- **Valid Token**: User is authenticated and granted access to `/api/books`.
- **Invalid/Expired Token**: The filter does not set the authentication, and Spring Security responds with `401 Unauthorized`.

### **Potential Improvements and Considerations**

1. **Endpoint Path Consistency**:
   - The filter excludes paths containing `/api/v1/auth`, but the controller is mapped to `/auth`. Ensure consistency in path prefixes to avoid unintended filtering.
   - **Recommendation**: Adjust the path check to match your actual authentication endpoints.

   **Example**:
   ```java
   if (request.getServletPath().startsWith("/auth")) {
       filterChain.doFilter(request, response);
       return;
   }
   ```

2. **SecurityContext Authentication Check**:
   - **Current Check**: `!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()`
   - **Issue**: If the authentication is not set (`getAuthentication()` returns `null`), calling `isAuthenticated()` will throw a `NullPointerException`.
   - **Solution**: Modify the condition to handle `null` authentication.

   **Updated Condition**:
   ```java
   if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
       // Proceed with authentication
   }
   ```

3. **Exception Handling**:
   - **Current Implementation**: Any exceptions during JWT extraction or validation will propagate, potentially leading to generic error responses.
   - **Recommendation**: Implement exception handling within the filter to provide meaningful error responses.

   **Example**:
   ```java
   try {
       // JWT extraction and validation logic
   } catch (JwtException e) {
       response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
       response.getWriter().write("Invalid or expired JWT token");
       return;
   }
   ```

4. **Optimizing Token Parsing**:
   - **Repeated Parsing**: If multiple filters or components need to parse the JWT, consider optimizing by caching claims or using a centralized service.

---

## 7. **User Details Service Implementation**

### **Code Overview**

```java
package com.wchamara.book.security;

import com.wchamara.book.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may be case-sensitive, or case-insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested.
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
```

### **Explanation**

- **Class and Annotations**:
  - **`@Service`**: Marks this class as a Spring service component.
  - **`@RequiredArgsConstructor`**: Lombok annotation to generate a constructor with required arguments.

- **Implementation**:
  - **`UserDetailsService`**: An interface provided by Spring Security to load user-specific data.

- **`loadUserByUsername` Method**:
  - **Purpose**: Locates the user based on the provided username (in this case, email) and returns a `UserDetails` object containing user information and authorities.
  
  - **Parameters**:
    - **`String username`**: The username (email) of the user attempting to authenticate.
  
  - **Return Type**:
    - **`UserDetails`**: An interface that provides core user information to Spring Security.
  
  - **Logic**:
    1. **User Lookup**:
       - **`userRepository.findByEmail(username)`**: Searches for a user with the given email.
    2. **Exception Handling**:
       - **`orElseThrow`**: If no user is found, throws a `UsernameNotFoundException`, signaling Spring Security that authentication should fail.

### **Practical Example**

**Scenario**: A user attempts to log in with email `jane.doe@example.com`.

**Process Flow:**

1. **Authentication Request**:
   - User submits login credentials (email and password).

2. **Spring Security Integration**:
   - Spring Security invokes `loadUserByUsername("jane.doe@example.com")` to retrieve user details.

3. **User Lookup**:
   - **`UserRepository.findByEmail("jane.doe@example.com")`**:
     - **If Found**: Returns the `User` entity, which implements `UserDetails`.
     - **If Not Found**: Throws `UsernameNotFoundException`, leading to an authentication failure.

4. **Authentication Decision**:
   - **Successful Lookup**: Proceeds to password validation.
   - **Failed Lookup**: Responds with `401 Unauthorized`.

### **Integration with Spring Security**

Given that `User` implements `UserDetails`, returning the `User` entity directly satisfies the `UserDetailsService` contract.

**Example in `SecurityConfig`:**

```java
@Autowired
private UserDetailServiceImpl userDetailsService;

@Bean
public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
}
```

**Explanation:**

- **`DaoAuthenticationProvider`**:
  - **Purpose**: An `AuthenticationProvider` implementation that uses a `UserDetailsService` to retrieve user information and a `PasswordEncoder` to validate passwords.
  
- **Configuration**:
  - **`setUserDetailsService(userDetailsService)`**: Sets the custom `UserDetailsService` (`UserDetailServiceImpl`) for user retrieval.
  - **`setPasswordEncoder(passwordEncoder())`**: Sets the `PasswordEncoder` to validate user passwords.

**Authentication Flow:**

1. **User submits login credentials**.
2. **Spring Security** uses `DaoAuthenticationProvider` to:
   - Invoke `UserDetailServiceImpl.loadUserByUsername(email)` to fetch user details.
   - Validate the submitted password against the stored (hashed) password using `PasswordEncoder`.
3. **Authentication Decision**:
   - **Success**: Sets the authentication in the `SecurityContext`.
   - **Failure**: Returns `401 Unauthorized`.

---

## 8. **Beans Configuration**

### **Code Overview**

```java
package com.wchamara.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
```

### **Explanation**

- **Class and Annotations**:
  - **`@Configuration`**: Indicates that this class contains bean definitions.
  - **`@RequiredArgsConstructor`**: Lombok annotation to generate a constructor with required arguments.

- **Dependencies**:
  - **`private final UserDetailsService userDetailsService;`**: Injected service to load user details during authentication.

- **Beans Defined**:
  1. **`AuthenticationProvider` Bean**:
     - **Type**: `DaoAuthenticationProvider`
     - **Purpose**: An `AuthenticationProvider` that uses a `UserDetailsService` and a `PasswordEncoder` to authenticate users.
     - **Configuration**:
       - **`setUserDetailsService(userDetailsService)`**: Sets the service to load user details.
       - **`setPasswordEncoder(passwordEncoder())`**: Sets the password encoder for validating passwords.
  
  2. **`PasswordEncoder` Bean**:
     - **Type**: `BCryptPasswordEncoder`
     - **Purpose**: Encodes and verifies passwords using the BCrypt hashing algorithm.
     - **Configuration**:
       - **Default Constructor**: Uses default strength (10). You can customize the strength if needed.

### **Practical Example**

**User Registration and Authentication**:

1. **Registration**:
   - User registers with a password.
   - **Password Encoding**: `passwordEncoder.encode(request.getPassword())` hashes the password using BCrypt before saving to the database.

2. **Authentication**:
   - User submits login credentials.
   - **AuthenticationProvider** (`DaoAuthenticationProvider`) uses:
     - **`UserDetailsService`**: Loads user details by email.
     - **`PasswordEncoder`**: Compares the submitted password (after encoding) with the stored hashed password.

3. **Security Context**:
   - Upon successful authentication, Spring Security sets the authentication in the `SecurityContext`, allowing access to secured resources.

### **Customization Options**

- **Password Encoder Strength**:
  - **Default**: BCrypt with strength 10.
  - **Customization**: You can specify a different strength based on security requirements.

  **Example**:
  ```java
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(12); // Strength 12
  }
  ```

- **Alternative Password Encoders**:
  - Spring Security supports various password encoders like `Pbkdf2PasswordEncoder`, `Argon2PasswordEncoder`, etc.
  - **Usage**: Replace `BCryptPasswordEncoder` with the desired encoder.

  **Example**:
  ```java
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new Argon2PasswordEncoder();
  }
  ```

---

## 9. **JWT Service**

### **Code Overview**

```java
package com.wchamara.book.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.expiration}")
    private Long jwtExpirationInMs;

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);


    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSignedKey()).build().parseSignedClaims(token).getPayload();
    }

    private String generateToken(Map<String, Object> claims, UserDetails userDetails) {

        return buildToke(claims, userDetails, jwtExpirationInMs);
    }

    private String buildToke(Map<String, Object> extraClaims, UserDetails userDetails, Long jwtExpirationInMs) {
        var authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .claim("authorities", authorities)
                .signWith(getSignedKey())
                .compact();

    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private SecretKey getSignedKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
```

### **Explanation**

- **Annotations**:
  - **`@Service`**: Marks this class as a Spring service component.

- **Configuration Properties**:
  - **`@Value("${application.security.jwt.expiration}")`**:
    - **Purpose**: Injects the JWT expiration time from `application.yml`.
    - **Value Example**: `86400000` milliseconds (24 hours).
  - **`@Value("${application.security.jwt.secret-key}")`**:
    - **Purpose**: Injects the JWT secret key from `application.yml`.
    - **Value Example**: A Base64-encoded secret string.

- **Methods**:
  1. **`generateToken(UserDetails userDetails)`**:
     - **Purpose**: Generates a JWT token for the given user.
     - **Usage**: Called during authentication to issue a JWT.
  
  2. **`extractUsername(String token)`**:
     - **Purpose**: Extracts the username (subject) from the JWT.
     - **Usage**: Used in JWT validation and retrieval.

  3. **`validateToken(String token, UserDetails userDetails)`**:
     - **Purpose**: Validates the JWT token against the user details.
     - **Checks**:
       - The token's subject matches the user's username.
       - The token is not expired.
  
  4. **`extractClaim(String token, Function<Claims, T> claimsResolver)`**:
     - **Purpose**: Extracts a specific claim from the JWT using a resolver function.
     - **Generic**: Can extract any claim based on the provided function.

  5. **`extractAllClaims(String token)`**:
     - **Purpose**: Parses the JWT and retrieves all claims.
     - **Implementation**:
       - **`Jwts.parser()`**: Creates a JWT parser.
       - **`verifyWith(getSignedKey())`**: Sets the signing key for signature verification.
       - **`parseSignedClaims(token)`**: Parses the token and retrieves the claims.

  6. **`buildToke` (Typo: Should be `buildToken`)**:
     - **Purpose**: Constructs and signs the JWT with claims and user information.
     - **Parameters**:
       - **`extraClaims`**: Additional claims to include in the token.
       - **`userDetails`**: User information to include (e.g., username).
       - **`jwtExpirationInMs`**: Token expiration time.
     - **Implementation**:
       - **`setSubject(userDetails.getUsername())`**: Sets the token subject to the user's username.
       - **`setIssuedAt`**: Sets the issuance time.
       - **`setExpiration`**: Sets the token's expiration time.
       - **`claim("authorities", authorities)`**: Adds the user's authorities/roles as a claim.
       - **`signWith(getSignedKey())`**: Signs the token using the secret key.
       - **`compact()`**: Builds the JWT as a compact string.

  7. **`isTokenExpired(String token)`**:
     - **Purpose**: Checks if the token has expired.
     - **Usage**: Used in token validation.

  8. **`extractExpiration(String token)`**:
     - **Purpose**: Extracts the expiration date from the token.

  9. **`getSignedKey()`**:
     - **Purpose**: Retrieves the signing key from the Base64-encoded secret.
     - **Implementation**:
       - **`Decoders.BASE64.decode(secretKey)`**: Decodes the Base64-encoded secret.
       - **`Keys.hmacShaKeyFor(keyBytes)`**: Generates a `SecretKey` suitable for HMAC-SHA algorithms.

### **Potential Issues and Corrections**

1. **Method Typo**:
   - **Current**: `buildToke`
   - **Correction**: Rename to `buildToken` for clarity and to avoid confusion.

2. **JWT Parsing Method**:
   - **Current**:
     ```java
     return Jwts.parser().verifyWith(getSignedKey()).build().parseSignedClaims(token).getPayload();
     ```
   - **Potential Issue**: Earlier, the user faced an issue with `parseClaimsJws` not being resolved. In JJWT `0.12.6`, the correct method is `parseClaimsJws`.
   - **Correction**: Update to use `parseClaimsJws` appropriately.

   **Corrected `extractAllClaims` Method**:
   ```java
   private Claims extractAllClaims(String token) {
       return Jwts.parserBuilder()
                  .setSigningKey(getSignedKey())
                  .build()
                  .parseClaimsJws(token)
                  .getBody();
   }
   ```

3. **Security of Secret Key**:
   - **Current**: `secret-key: secret`
   - **Issue**: A short and simple secret key is insecure.
   - **Recommendation**: Use a sufficiently long and random Base64-encoded secret key.

   **Example**:
   ```yaml
   application:
     security:
       jwt:
         secret-key: c2VjdXJlLXNlY3JldC1rZXktMTIzNDU2Nzg5MA== # Base64-encoded 256-bit key
         expiration: 86400000
         token-prefix: Bearer
         header-string: Authorization
   ```

   **Generating a Secure Secret Key**:
   ```java
   import io.jsonwebtoken.io.Encoders;
   import io.jsonwebtoken.security.Keys;

   // Generate a 256-bit key
   SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
   String base64Key = Encoders.BASE64.encode(key.getEncoded());
   System.out.println("Base64-encoded secret key: " + base64Key);
   ```

4. **Enhancing Token Claims**:
   - **Current**: Only `subject` and `authorities` are included.
   - **Recommendation**: Consider adding other claims like `issuer`, `audience`, `issuedAt`, etc., based on application requirements.

   **Example**:
   ```java
   return Jwts
           .builder()
           .setClaims(extraClaims)
           .setSubject(userDetails.getUsername())
           .setIssuer("your-app-name")
           .setAudience("your-app-audience")
           .setIssuedAt(new Date(System.currentTimeMillis()))
           .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
           .claim("authorities", authorities)
           .signWith(getSignedKey(), SignatureAlgorithm.HS256)
           .compact();
   ```

### **Complete Corrected `JwtService` Example**

```java
@Service
public class JwtService {

    @Value("${application.security.jwt.expiration}")
    private Long jwtExpirationInMs;

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(getSignedKey())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    private String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return buildToken(claims, userDetails, jwtExpirationInMs);
    }

    private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, Long jwtExpirationInMs) {
        var authorities = userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .toList();

        return Jwts.builder()
                   .setClaims(extraClaims)
                   .setSubject(userDetails.getUsername())
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                   .claim("authorities", authorities)
                   .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                   .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private SecretKey getSignedKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
```

---

## 10. **Application Configuration (`application.yml`)**

### **Code Overview**

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/book_social_network
    username: username
    password: password
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
  mail:
    host: localhost
    port: 1025
    username: username
    password: password
    properties:
      mail:
        smtp:
          trust: "*"
        auth: true
        starttls:
          enable: true
        connectiontimeout: 5000
        timeout: 3000
        writetimeout: 5000
application:
  security:
    jwt:
      secret-key: secret
      expiration: 86400000
      token-prefix: Bearer
      header-string: Authorization
```

### **Explanation**

This YAML configuration file sets up various aspects of the Spring Boot application, including the datasource, JPA, mail, and JWT security settings.

#### **1. Spring Datasource Configuration**

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/book_social_network
    username: username
    password: password
    driver-class-name: org.postgresql.Driver
```

- **Purpose**: Configures the connection to the PostgreSQL database.
- **Parameters**:
  - **`url`**: JDBC URL for the PostgreSQL database.
  - **`username` & `password`**: Credentials for database access.
  - **`driver-class-name`**: Specifies the PostgreSQL JDBC driver.

**Security Note**: Avoid hard-coding sensitive information like `username` and `password`. Use environment variables or externalized configuration for production environments.

#### **2. JPA Configuration**

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
    database: postgresql
    database-platform: org.hibernate.dialect.PostgreSQLDialect
```

- **`ddl-auto: update`**:
  - **Purpose**: Automatically updates the database schema based on entity changes.
  - **Options**:
    - **`validate`**: Validates the schema, making no changes.
    - **`update`**: Updates the schema without dropping existing data.
    - **`create`**: Creates the schema, dropping existing data.
    - **`create-drop`**: Creates the schema on startup and drops it on shutdown.
  - **Recommendation**: Use `update` for development and consider manual migrations (e.g., Flyway, Liquibase) for production.

- **`show-sql: false`**:
  - **Purpose**: Disables logging of SQL statements.
  - **Recommendation**: Set to `true` for debugging purposes during development.

- **`format_sql: true`**:
  - **Purpose**: Formats SQL statements for better readability in logs.

- **`database-platform`**:
  - **Purpose**: Specifies the Hibernate dialect for PostgreSQL, ensuring correct SQL generation.

#### **3. Mail Configuration**

```yaml
spring:
  mail:
    host: localhost
    port: 1025
    username: username
    password: password
    properties:
      mail:
        smtp:
          trust: "*"
        auth: true
        starttls:
          enable: true
        connectiontimeout: 5000
        timeout: 3000
        writetimeout: 5000
```

- **Purpose**: Configures the SMTP server for sending emails.
- **Parameters**:
  - **`host` & `port`**: SMTP server details.
  - **`username` & `password`**: Credentials for SMTP authentication.
  - **`properties.mail.smtp`**:
    - **`trust: "*"`**: Trusts all SMTP hosts. **Caution**: Insecure; specify trusted hosts explicitly.
    - **`auth: true`**: Enables SMTP authentication.
    - **`starttls.enable: true`**: Enables TLS encryption for SMTP.
    - **Timeouts**:
      - **`connectiontimeout`**: Maximum time to establish a connection.
      - **`timeout`**: Maximum time to wait for a response.
      - **`writetimeout`**: Maximum time to wait for data writing.

**Use Case**: Configured for a local SMTP server (e.g., [MailHog](https://github.com/mailhog/MailHog)) during development.

#### **4. JWT Security Configuration**

```yaml
application:
  security:
    jwt:
      secret-key: secret
      expiration: 86400000
      token-prefix: Bearer
      header-string: Authorization
```

- **`secret-key`**:
  - **Purpose**: The secret key used to sign and verify JWTs.
  - **Security Note**: 
    - **Current Value**: `"secret"` is insecure.
    - **Recommendation**: Use a strong, randomly generated Base64-encoded key of sufficient length (at least 256 bits for HS256).

- **`expiration`**:
  - **Purpose**: JWT token expiration time in milliseconds.
  - **Value**: `86400000` milliseconds equals 24 hours.

- **`token-prefix`**:
  - **Purpose**: Prefix for the JWT in the `Authorization` header.
  - **Value**: `"Bearer"` (standard convention).

- **`header-string`**:
  - **Purpose**: The HTTP header used to pass the JWT.
  - **Value**: `"Authorization"` (standard convention).

**Enhanced Configuration Example**:

```yaml
application:
  security:
    jwt:
      secret-key: c2VjdXJlLXNlY3JldC1rZXktMTIzNDU2Nzg5MA== # Base64-encoded 256-bit key
      expiration: 86400000
      token-prefix: Bearer
      header-string: Authorization
```

**Generating a Secure Secret Key**:

```java
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

// Generate a 256-bit (32-byte) key
SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
String base64Key = Encoders.BASE64.encode(key.getEncoded());
System.out.println("Base64-encoded secret key: " + base64Key);
```

**Output Example**:

```
Base64-encoded secret key: c2VjdXJlLXNlY3JldC1rZXktMTIzNDU2Nzg5MA==
```

**Security Note**: Store the secret key securely, avoiding hard-coding in configuration files. Use environment variables or secret management services.

---

## 11. **Integration and Workflow**

Let's map out how the components interact to facilitate user registration, authentication, and authorization.

### **1. User Registration Flow**

**Steps**:

1. **Client Registration Request**:
   - **Endpoint**: `POST /auth/register`
   - **Payload**:
     ```json
     {
         "firstname": "Alice",
         "lastname": "Wonderland",
         "email": "alice@example.com",
         "password": "SecureP@ssw0rd!"
     }
     ```

2. **AuthenticationController**:
   - Receives the request.
   - Validates the payload using `@Valid` annotations in `RegistrationRequest`.
   - Delegates to `AuthenticationService.register(request)`.

3. **AuthenticationService**:
   - **User Existence Check**: Uses `UserRepository.findByEmail(email)` to ensure the email isn't already registered.
   - **Role Assignment**: Fetches the "USER" role using `RoleRepository.findByName("USER")`.
   - **Password Encryption**: Hashes the password using `PasswordEncoder`.
   - **User Creation**: Creates a new `User` entity with `enabled = false` and assigns the "USER" role.
   - **Save User**: Persists the user to the database.
   - **Token Generation**: Creates a verification `Token` using `TokenService`.
   - **Email Sending**: Sends a verification email with the token using `EmailService`.

4. **Response**:
   - **Status**: `202 Accepted`
   - **Message**: "User registered successfully. Please check your email for verification."

### **2. Email Verification Flow**

**Steps**:

1. **User Clicks Verification Link**:
   - **Link Structure**: `GET /api/auth/verify?token=<JWT_TOKEN>`
   - **Assumption**: The email sent to the user contains a link like the above.

2. **Verification Endpoint**:
   - **Controller**: Typically handled by another endpoint in `AuthenticationController` (not provided in the snippets).
   - **Action**:
     - Extracts the token from the request.
     - Validates the token using `JwtService`.
     - Retrieves the associated user.
     - Sets `enabled = true` in the user entity.
     - Saves the updated user.

3. **SecurityContext Update**:
   - Upon successful verification, the user's account is active, allowing authentication.

### **3. User Authentication Flow**

**Steps**:

1. **Client Login Request**:
   - **Endpoint**: `POST /auth/login`
   - **Payload**:
     ```json
     {
         "email": "alice@example.com",
         "password": "SecureP@ssw0rd!"
     }
     ```

2. **AuthenticationController**:
   - Receives the request.
   - Delegates to `AuthenticationService.login(request)` (implementation not provided).

3. **AuthenticationService**:
   - **Authentication Logic**:
     - Authenticates the user using Spring Security's `AuthenticationManager`.
     - Generates a JWT token upon successful authentication.
   - **Response**:
     - Returns the JWT token to the client.

4. **Client Receives Token**:
   - **Usage**: Includes the JWT in the `Authorization` header for subsequent requests.

   **Example**:
   ```
   Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
   ```

5. **Secured Endpoint Access**:
   - **Filter Processing**: `JwtFilter` intercepts the request, extracts, and validates the JWT.
   - **Authentication**: Sets the authentication in the `SecurityContext` if valid.
   - **Controller Handling**: Proceeds to handle the secured request.

### **4. Accessing Secured Endpoints**

**Scenario**: Accessing `/api/books` with a valid JWT.

**Process**:

1. **HTTP Request**:
   ```
   GET /api/books
   Authorization: Bearer <JWT_TOKEN>
   ```

2. **Security Filter Chain**:
   - **CORS Handling**.
   - **CSRF Disabled**.
   - **Authorization Check**:
     - `/api/books` is not in the permitted paths; requires authentication.
   
3. **JWT Filter (`JwtFilter`)**:
   - Extracts the JWT.
   - Validates the token and extracts user details.
   - Sets the authentication in the `SecurityContext`.
   
4. **Controller Processing**:
   - Authenticated user accesses `/api/books`.
   - Controller processes the request and returns data.

---

## 12. **Best Practices and Recommendations**

Implementing a robust authentication system requires adherence to best practices to ensure security, maintainability, and scalability. Below are key recommendations based on the provided code.

### **1. Secure Secret Key Management**

- **Avoid Hard-Coding Secrets**: Never hard-code secret keys or sensitive information in your source code or configuration files.

- **Use Environment Variables or Secret Management Services**:
  - **Environment Variables**: Set secrets as environment variables and reference them in your configuration.
  - **Secret Management**: Utilize services like **HashiCorp Vault**, **AWS Secrets Manager**, or **Azure Key Vault** for enhanced security.

- **Example Using Environment Variables**:

  **`application.yml`:**
  ```yaml
  application:
    security:
      jwt:
        secret-key: ${JWT_SECRET_KEY}
        expiration: 86400000
        token-prefix: Bearer
        header-string: Authorization
  ```

  **Setting Environment Variable:**
  ```bash
  export JWT_SECRET_KEY=c2VjdXJlLXNlY3JldC1rZXktMTIzNDU2Nzg5MA==
  ```

### **2. Password Security**

- **Use Strong Password Encoders**:
  - **Current**: `BCryptPasswordEncoder`
  - **Recommendation**: Continue using `BCryptPasswordEncoder` as it's robust against brute-force attacks.

- **Password Policies**:
  - Enforce strong password policies (minimum length, complexity, etc.) using validation annotations, as done in `RegistrationRequest`.

- **Example**:
  ```java
  @Size(min = 12, message = "Password must be at least 12 characters long")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Password must contain lowercase, uppercase letters, and digits")
  private String password;
  ```

### **3. JWT Token Management**

- **Secure Secret Key**: Ensure the secret key is long and random enough to prevent brute-force attacks.

- **Token Expiration**:
  - **Short-Lived Tokens**: Use short expiration times to minimize risk if a token is compromised.
  - **Refresh Tokens**: Implement refresh tokens to allow users to obtain new access tokens without re-authenticating.

- **Claim Validation**:
  - **Issuer (`iss`)**: Validate the token issuer.
  - **Audience (`aud`)**: Validate the intended audience of the token.

- **Example**:
  ```java
  Jwts.builder()
      .setSubject(userDetails.getUsername())
      .setIssuer("your-app-name")
      .setAudience("your-app-audience")
      // Other claims
      .signWith(getSignedKey(), SignatureAlgorithm.HS256)
      .compact();
  ```

### **4. Exception Handling in JWT Filter**

- **Graceful Failure**: Handle exceptions during JWT parsing and validation to provide meaningful responses.

- **Example**:
  ```java
  @Override
  protected void doFilterInternal(
          @NonNull HttpServletRequest request,
          @NonNull HttpServletResponse response,
          @NonNull FilterChain filterChain) throws ServletException, IOException {

      try {
          // Existing JWT processing logic
      } catch (JwtException e) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          response.getWriter().write("Invalid or expired JWT token");
          return;
      }

      filterChain.doFilter(request, response);
  }
  ```

### **5. Logging and Monitoring**

- **Audit Logs**: Log authentication attempts, both successful and failed, for auditing purposes.
  
- **Monitor Suspicious Activities**: Detect and respond to unusual patterns, such as multiple failed login attempts.

- **Example Using SLF4J**:
  ```java
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;

  @Service
  public class JwtFilter extends OncePerRequestFilter {
      private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

      // Inside doFilterInternal
      catch (JwtException e) {
          logger.warn("JWT validation failed: {}", e.getMessage());
          // Handle response
      }
  }
  ```

### **6. Secure CORS Configuration**

- **Restrict Origins**: Specify allowed origins instead of allowing all.

- **Example**:
  ```java
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("https://your-domain.com"));
      configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
      configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
      configuration.setExposedHeaders(Arrays.asList("Authorization"));
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
  }
  ```

### **7. Method-Level Security**

- **Annotations**: Utilize method-level security annotations like `@Secured`, `@PreAuthorize`, or `@PostAuthorize` to enforce access controls on specific methods.

- **Example**:
  ```java
  @Service
  public class BookService {

      @Secured("ROLE_ADMIN")
      public void deleteBook(Integer bookId) {
          // Deletion logic
      }
  }
  ```

### **8. API Documentation**

- **Swagger/OpenAPI**: Utilize Swagger annotations (as seen with `@Tag`) to generate comprehensive API documentation.

- **Configuration**:
  - **Include Swagger UI**: Ensure Swagger UI is properly configured and secured.
  - **Example**: The permitted paths in `SecurityConfig` include Swagger endpoints.

### **9. Database Migrations**

- **Use Migration Tools**: Employ tools like **Flyway** or **Liquibase** for managing database schema changes, ensuring version control and consistency across environments.

- **Example with Flyway**:

  **`pom.xml` Dependency**:
  ```xml
  <dependency>
      <groupId>org.flywaydb</groupId>
      <artifactId>flyway-core</artifactId>
  </dependency>
  ```

  **Migration Script (`db/migration/V1__Initial_Setup.sql`)**:
  ```sql
  CREATE TABLE roles (
      id SERIAL PRIMARY KEY,
      name VARCHAR(50) UNIQUE NOT NULL,
      created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      last_modified_date_time TIMESTAMP
  );

  CREATE TABLE users (
      id SERIAL PRIMARY KEY,
      firstname VARCHAR(50) NOT NULL,
      lastname VARCHAR(50) NOT NULL,
      email VARCHAR(100) UNIQUE NOT NULL,
      password VARCHAR(100) NOT NULL,
      account_locked BOOLEAN DEFAULT FALSE,
      enabled BOOLEAN DEFAULT FALSE,
      created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      last_modified_date_time TIMESTAMP
  );

  CREATE TABLE tokens (
      id SERIAL PRIMARY KEY,
      token VARCHAR(255) NOT NULL,
      created_at TIMESTAMP NOT NULL,
      expires_at TIMESTAMP NOT NULL,
      validated_at TIMESTAMP,
      user_id INTEGER NOT NULL,
      FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
  );

  CREATE TABLE user_roles (
      user_id INTEGER NOT NULL,
      role_id INTEGER NOT NULL,
      PRIMARY KEY (user_id, role_id),
      FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
      FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
  );
  ```

### **10. Testing**

- **Unit Tests**: Write unit tests for services, ensuring individual components behave as expected.

- **Integration Tests**: Test the interaction between components, such as controllers and services, with a real database (use in-memory databases like H2 for testing).

- **Example Unit Test for `JwtService`**:

  ```java
  @ExtendWith(SpringExtension.class)
  @SpringBootTest
  public class JwtServiceTest {

      @Autowired
      private JwtService jwtService;

      @Autowired
      private UserRepository userRepository;

      @Autowired
      private PasswordEncoder passwordEncoder;

      @Test
      public void testGenerateAndValidateToken() {
          User user = User.builder()
                  .firstname("Test")
                  .lastname("User")
                  .email("test.user@example.com")
                  .password(passwordEncoder.encode("TestPass123"))
                  .enabled(true)
                  .build();
          userRepository.save(user);

          UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                  user.getEmail(),
                  user.getPassword(),
                  List.of(new SimpleGrantedAuthority("ROLE_USER"))
          );

          String token = jwtService.generateToken(userDetails);
          assertNotNull(token);

          String extractedUsername = jwtService.extractUsername(token);
          assertEquals(user.getEmail(), extractedUsername);

          boolean isValid = jwtService.validateToken(token, userDetails);
          assertTrue(isValid);
      }
  }
  ```

### **11. Exception Handling and Global Error Responses**

- **Controller Advice**: Implement a `@ControllerAdvice` to handle exceptions globally, ensuring consistent error responses.

- **Example**:

  ```java
  @ControllerAdvice
  public class GlobalExceptionHandler {

      @ExceptionHandler(UsernameNotFoundException.class)
      public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
          return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
      }

      @ExceptionHandler(IllegalArgumentException.class)
      public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
          return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
      }

      // Handle other exceptions
  }
  ```

### **12. Ensuring HTTPS**

- **Secure Transport**: Always serve your application over HTTPS to encrypt data in transit, especially JWT tokens and sensitive user information.

- **Configuration**: 
  - Use SSL certificates.
  - Configure your server (e.g., Tomcat) to support HTTPS.

- **Example in `application.yml`**:

  ```yaml
  server:
    ssl:
      key-store: classpath:keystore.jks
      key-store-password: changeit
      key-password: changeit
  ```

---

## 13. **Conclusion**

The provided code snippets collectively establish a robust JWT-based authentication system within a Spring Boot application. Here's a summary of the key components and their roles:

- **Project Dependencies**: Configured JJWT library for JWT operations, ensuring version consistency.

- **Authentication Controller**: Handles user registration requests, delegating to the `AuthenticationService`.

- **Authentication Service**: Manages the business logic for user registration, including user creation, role assignment, token generation, and email sending.

- **Registration Request DTO**: Defines the structure and validation constraints for user registration data.

- **Security Configuration**: Sets up Spring Security with JWT-based authentication, defining secured and permitted endpoints, session management, and integrating the JWT filter.

- **JWT Filter**: Intercepts incoming requests, extracts and validates JWT tokens, and sets the authentication context.

- **User Details Service Implementation**: Loads user details from the database based on the provided username (email), enabling Spring Security to authenticate users.

- **Beans Configuration**: Defines essential beans like `AuthenticationProvider` and `PasswordEncoder` for authentication processes.

- **JWT Service**: Handles JWT generation, extraction, and validation, ensuring secure token management.

- **Application Configuration**: Configures datasource, JPA, mail settings, and JWT security parameters via `application.yml`.

### **Key Takeaways and Best Practices**:

1. **Security First**:
   - Protect sensitive data like secret keys and user passwords.
   - Use strong, randomly generated secret keys for JWT signing.
   - Enforce robust password policies and use secure hashing algorithms.

2. **Validation and Exception Handling**:
   - Implement comprehensive validation on user inputs.
   - Handle exceptions gracefully, providing meaningful feedback to clients.

3. **Modular Design**:
   - Separate concerns by organizing code into controllers, services, repositories, and security configurations.
   - Utilize Spring's dependency injection and Lombok to reduce boilerplate and enhance maintainability.

4. **Testing and Monitoring**:
   - Write unit and integration tests to ensure functionality.
   - Monitor authentication processes and log relevant events for auditing and troubleshooting.

5. **Documentation and API Clarity**:
   - Use Swagger/OpenAPI annotations to document APIs, aiding both development and integration efforts.

6. **Use of Modern Libraries and Practices**:
   - Leverage Spring Security's powerful features for authentication and authorization.
   - Utilize JJWT's latest APIs for secure and efficient JWT handling.

7. **Performance and Scalability**:
   - Optimize security filters to handle high traffic efficiently.
   - Implement caching strategies if necessary for user details or tokens.

By adhering to these practices and continuously refining your authentication system based on evolving requirements and security standards, you can build a secure, efficient, and user-friendly application.

---

Feel free to ask if you need further clarification on any component or additional examples to enhance your understanding!

# Implementing the Enail Sender Service

```java
package com.wchamara.book.auth;

import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;


    public void register(RegistrationRequest request) {
        Role userRole = roleRepository.findByName("USER").orElseThrow(
                () -> new IllegalStateException("Role user was not initialized")
        );

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) {
        var token = generateAndSaveActivationToken(user);

        // send email
        return;

    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);

        Token token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;

    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(characters.length());
            char charAt = characters.charAt(index);
            codeBuilder.append(charAt);
        }

        return codeBuilder.toString();
    }
}
```

Certainly! Let's delve deeply into the provided Java `AuthenticationService` class, which is part of a Spring-based application responsible for handling user registration and authentication. We'll break down each component, explain its purpose, and provide examples to illustrate how the service operates within the application.

## Overview

The `AuthenticationService` class is a Spring `@Service` that manages user registration, including role assignment, password encoding, activation token generation, and sending validation emails. Here's a high-level view of what the class does:

1. **Registers a new user**: Takes registration details, encodes the password, assigns a default role, and saves the user to the database.
2. **Generates an activation token**: Creates a unique activation code for email validation.
3. **Sends a validation email**: Sends the activation token to the user's email for account verification.

Let's break down each part of the code.

## Package and Imports

```java
package com.wchamara.book.auth;

import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
```

### Explanation

- **Package Declaration**: The class is part of the `com.wchamara.book.auth` package, indicating it's related to authentication within the `book` application.

- **Imports**: The class imports necessary classes for user, role, and token management (`User`, `Role`, `Token`, and their respective repositories). It also imports `PasswordEncoder` for encoding passwords, `Service` and `RequiredArgsConstructor` annotations from Spring and Lombok respectively, and utility classes like `SecureRandom` and `LocalDateTime`.

### Example

Assume the application manages books, users, and roles (e.g., USER, ADMIN). This service handles the creation of new users and their initial setup.

## Class Declaration and Annotations

```java
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    // ...
}
```

### Explanation

- **@Service**: Marks the class as a Spring service component, making it eligible for component scanning and dependency injection.

- **@RequiredArgsConstructor**: A Lombok annotation that generates a constructor with required arguments (i.e., final fields). This facilitates dependency injection without boilerplate code.

### Example

When Spring initializes the application context, it detects `AuthenticationService` as a service and injects its dependencies automatically via the generated constructor.

## Dependencies

```java
private final RoleRepository roleRepository;
private final PasswordEncoder passwordEncoder;
private final UserRepository userRepository;
private final TokenRepository tokenRepository;
```

### Explanation

These are the dependencies required by the `AuthenticationService`:

- **RoleRepository**: Interface for CRUD operations on `Role` entities.
- **PasswordEncoder**: Interface for encoding passwords securely.
- **UserRepository**: Interface for CRUD operations on `User` entities.
- **TokenRepository**: Interface for CRUD operations on `Token` entities.

### Example

When registering a new user, the service needs to fetch the default role (`USER`), encode the user's password, save the user to the database, and create an activation token.

## The `register` Method

```java
public void register(RegistrationRequest request) {
    Role userRole = roleRepository.findByName("USER").orElseThrow(
            () -> new IllegalStateException("Role user was not initialized")
    );

    User user = User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .accountLocked(false)
            .enabled(false)
            .roles(List.of(userRole))
            .build();

    userRepository.save(user);
    sendValidationEmail(user);
}
```

### Explanation

1. **Fetching the User Role**:
    - The service retrieves the `USER` role from the `RoleRepository`.
    - If the role isn't found, it throws an `IllegalStateException`, indicating a misconfiguration.

2. **Building the User Object**:
    - Uses a builder pattern (via Lombok's `@Builder`) to create a new `User` instance.
    - Sets the user's first name, last name, email, and encodes the password using `PasswordEncoder`.
    - Initializes `accountLocked` to `false` and `enabled` to `false` (account not yet activated).
    - Assigns the `USER` role to the new user.

3. **Saving the User**:
    - Persists the new user to the database via `UserRepository`.

4. **Sending Validation Email**:
    - Calls `sendValidationEmail(user)` to initiate the account activation process.

### Example

Imagine a user named Jane Doe wants to register:

- She submits her first name, last name, email, and password via a registration form.
- The service encodes her password, assigns the `USER` role, saves her data to the database with `enabled` set to `false`.
- An activation token is generated and emailed to Jane for account verification.

## The `sendValidationEmail` Method

```java
private void sendValidationEmail(User user) {
    var token = generateAndSaveActivationToken(user);

    // send email
    return;
}
```

### Explanation

1. **Generating and Saving the Activation Token**:
    - Calls `generateAndSaveActivationToken(user)` to create a unique token associated with the user.

2. **Sending the Email**:
    - Placeholder comment indicates where the email-sending logic would be implemented.
    - Typically, this would involve using an email service to send the activation token to the user's email address.

### Example

Continuing with Jane Doe's registration:

- An activation token (e.g., "123456") is generated and saved to the database linked to Jane's user record.
- An email is sent to Jane containing the activation code or a link with the token for account verification.

## The `generateAndSaveActivationToken` Method

```java
private String generateAndSaveActivationToken(User user) {
    String generatedToken = generateActivationCode(6);

    Token token = Token.builder()
            .token(generatedToken)
            .createdAt(LocalDateTime.now())
            .expiresAt(LocalDateTime.now().plusMinutes(15))
            .user(user)
            .build();
    tokenRepository.save(token);

    return generatedToken;
}
```

### Explanation

1. **Generating the Token**:
    - Calls `generateActivationCode(6)` to create a 6-digit numerical activation code.

2. **Building the Token Object**:
    - Uses a builder to create a new `Token` instance.
    - Sets the token value, creation time (`createdAt`), expiration time (`expiresAt`), and associates it with the user.

3. **Saving the Token**:
    - Persists the token to the database via `TokenRepository`.

4. **Returning the Token**:
    - Returns the generated token string for further use (e.g., inclusion in the validation email).

### Example

For Jane Doe:

- Generates a token like "835192".
- Saves this token with the current timestamp and an expiration time 15 minutes later.
- Returns "835192" to be included in the activation email.

## The `generateActivationCode` Method

```java
private String generateActivationCode(int length) {
    String characters = "0123456789";
    StringBuilder codeBuilder = new StringBuilder();
    SecureRandom secureRandom = new SecureRandom();

    for (int i = 0; i < length; i++) {
        int index = secureRandom.nextInt(characters.length());
        char charAt = characters.charAt(index);
        codeBuilder.append(charAt);
    }

    return codeBuilder.toString();
}
```

### Explanation

1. **Character Set**:
    - Defines the characters used in the activation code. Here, only digits (0-9) are used.

2. **SecureRandom**:
    - Uses `SecureRandom` for generating cryptographically strong random numbers, enhancing security.

3. **Building the Code**:
    - Iterates `length` times (6 in this case).
    - In each iteration, selects a random character from the `characters` string and appends it to `codeBuilder`.

4. **Returning the Code**:
    - Converts the `StringBuilder` to a `String` and returns it as the activation code.

### Example

For a length of 6:

- Possible activation codes include "482093", "174829", "560391", etc.
- Each code is randomly generated and hard to predict, ensuring security.

## Putting It All Together: Registration Flow Example

Let's walk through a complete registration flow using the `AuthenticationService`.

### Scenario

1. **User Registration**:
    - **User Input**: John Smith submits a registration form with the following details:
        - First Name: John
        - Last Name: Smith
        - Email: john.smith@example.com
        - Password: securePassword123

2. **Registration Request Handling**:
    - **RegistrationRequest**: An instance containing the user's input is created and passed to the `register` method.

3. **Role Assignment**:
    - The service fetches the `USER` role from `RoleRepository`.
    - If the role doesn't exist, registration fails with an exception.

4. **User Creation**:
    - A new `User` object is built with the provided details.
    - The password is encoded (e.g., hashed) using `PasswordEncoder` for security.
    - `accountLocked` is set to `false`, and `enabled` is set to `false` to indicate that the account is not yet active.
    - The `USER` role is assigned to the user.
    - The user is saved to the database via `UserRepository`.

5. **Activation Token Generation**:
    - The service generates a 6-digit activation code (e.g., "827364") using `SecureRandom`.
    - A new `Token` object is created with the token value, current timestamp, expiration time (15 minutes later), and associated with the user.
    - The token is saved to the database via `TokenRepository`.

6. **Sending Validation Email**:
    - An email is sent to `john.smith@example.com` containing the activation code "827364" or a link with the token.
    - This step ensures that the user owns the provided email address and verifies their identity.

7. **Account Activation**:
    - **User Action**: John receives the email and clicks on the activation link or enters the activation code.
    - **Service Handling**: Another service method (not shown) validates the token:
        - Checks if the token exists and is associated with the user.
        - Verifies that the token hasn't expired.
        - If valid, sets `enabled` to `true` for the user, allowing them to log in.
        - Optionally deletes or invalidates the token to prevent reuse.

### Example Flow Diagram

1. **User Registration Form** → 
2. **AuthenticationService.register()** → 
3. **Fetch USER Role** → 
4. **Create User** → 
5. **Save User** → 
6. **Generate Token** → 
7. **Save Token** → 
8. **Send Validation Email** → 
9. **User Receives Email and Activates Account** → 
10. **Enable User Account**

## Security Considerations

1. **Password Encoding**:
    - **Importance**: Storing plain-text passwords is a severe security risk.
    - **Implementation**: Uses `PasswordEncoder` (e.g., BCrypt) to hash passwords before saving them.
    - **Example**: If John’s password is "securePassword123", it might be stored as `$2a$10$E9h8...` (hashed).

2. **Activation Token Security**:
    - **Randomness**: Uses `SecureRandom` to prevent predictability of tokens.
    - **Expiration**: Tokens expire after 15 minutes, reducing the window for potential misuse.
    - **Uniqueness**: Ensures that each token is unique per user and not reused.

3. **Account States**:
    - **accountLocked**: Prevents login attempts if set to `true`, useful for security measures like locking accounts after multiple failed login attempts.
    - **enabled**: Controls whether the user can log in. Only set to `true` after successful email verification.

4. **Exception Handling**:
    - Throws an exception if the `USER` role isn't initialized, ensuring that the application doesn't proceed with incomplete configurations.

## Potential Enhancements

1. **Email Sending Implementation**:
    - The `sendValidationEmail` method currently has a placeholder. Implementing actual email sending (e.g., using Spring's `JavaMailSender`) is essential for production.

    ```java
    private final EmailService emailService; // Assume EmailService is defined

    private void sendValidationEmail(User user) {
        var token = generateAndSaveActivationToken(user);
        String subject = "Activate Your Account";
        String message = "Your activation code is: " + token;
        emailService.sendEmail(user.getEmail(), subject, message);
    }
    ```

2. **Token Reuse Prevention**:
    - Ensure tokens are single-use by deleting them after successful activation.
    - Prevent generating multiple tokens for the same user within a short timeframe.

3. **Enhanced Token Security**:
    - Use longer and more complex tokens (e.g., UUIDs or alphanumeric strings) to increase security.
    - Implement rate limiting to prevent brute-force attempts on activation codes.

4. **User Feedback**:
    - Provide clear feedback to users during registration, activation, and error scenarios.
    - Handle edge cases, such as expired tokens or invalid activation attempts.

5. **Logging and Monitoring**:
    - Implement logging for registration attempts, token generation, and activation processes to monitor and audit activities.

6. **Internationalization (i18n)**:
    - Support multiple languages in emails and messages for a broader user base.

## Conclusion

The `AuthenticationService` class provides a robust foundation for user registration and account activation within a Spring-based application. By leveraging Spring's dependency injection, Lombok for reducing boilerplate code, and secure practices like password encoding and secure token generation, the service ensures both functionality and security.

Understanding each component and its role allows for effective maintenance, extension, and enhancement of the authentication system. Implementing the suggested enhancements can further strengthen the service, making it more secure, user-friendly, and scalable.

# Let's Implement the Email Service

```java
package com.wchamara.book.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(
            String to,
            String username,
            EmailTemplateName emailTemplateName,
            String confirmationUrl,
            String activationCode,
            String subject

    ) throws MessagingException {
        String templateName;

        if (emailTemplateName == null) {
            templateName = "confirm_email";
        } else {
            templateName = emailTemplateName.getName();
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activation_code", activationCode);

        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom("ccweerasinghe1994@gmail.com");
        helper.setSubject(subject);
        helper.setTo(to);

        String template = templateEngine.process(templateName, context);

        helper.setText(template, true);

        mailSender.send(mimeMessage);

    }

}

```

```java
package com.wchamara.book.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account");

    private final String name;

    EmailTemplateName(String name) {
        this.name = name;
    }
}

```

```java
package com.wchamara.book.auth;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {

        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }


}

```

```java
package com.wchamara.book.auth;

import com.wchamara.book.email.EmailService;
import com.wchamara.book.email.EmailTemplateName;
import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    @Value("${application.mailing.frontend}")
    private String activationUrl;


    public void register(RegistrationRequest request) throws MessagingException {
        Role userRole = roleRepository.findByName("USER").orElseThrow(
                () -> new IllegalStateException("Role user was not initialized")
        );

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var token = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                token,
                "Account Activation"
        );
        
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);

        Token token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;

    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(characters.length());
            char charAt = characters.charAt(index);
            codeBuilder.append(charAt);
        }

        return codeBuilder.toString();
    }
}

```

```java
package com.wchamara.book;

import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class BookNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookNetworkApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {

            if (roleRepository.findByName("USER").isEmpty()) {
                roleRepository.save(Role.builder()
                        .name("USER")
                        .build());
            }

        };
    }

}

```
activate_account.html
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Account Activation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 600px;
            margin: 10px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .activation-code {
            font-size: 36px;
            text-align: center;
            margin-bottom: 20px;
        }

        .activation-link {
            display: block;
            text-align: center;
            margin-top: 20px;
        }

        .activation-link a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Account Activation</h1>
    <p class="greeting" th:text="'Hello ' + ${username} + ','"></p>
    <p>Thank you for signing up! Please use the following activation code to activate your account:</p>
    <div class="activation-code"><span th:text="${activation_code}"></span></div>
    <div class="activation-link">
        <a target="_blank" th:href="${confirmationUrl}">Activate your account</a>
    </div>
</div>
</body>
</html>
```

application.yml
```yml
  mailing:
    frontend: http://localhost:4200/activate-account

server:
  port: 8088
```

Certainly! Let's delve deeply into your Spring Boot application, focusing on the registration and email activation process. We'll explore each provided code snippet, understand its role within the application, identify potential issues, and offer actionable solutions with examples. This comprehensive analysis aims to help you resolve the current validation error related to the `email` field and enhance the overall robustness of your application.

---

## 🛠️ **Table of Contents**

1. [Application Overview](#application-overview)
2. [Detailed Code Analysis](#detailed-code-analysis)
    - [1. `EmailService` Class](#1-emailservice-class)
    - [2. `EmailTemplateName` Enum](#2-emailtemplatename-enum)
    - [3. `AuthenticationController` Class](#3-authenticationcontroller-class)
    - [4. `AuthenticationService` Class](#4-authenticationservice-class)
    - [5. `BookNetworkApplication` Class](#5-booknetworkapplication-class)
    - [6. `activate_account.html` Template](#6-activate_accounthtml-template)
    - [7. `application.yml` Configuration](#7-applicationyml-configuration)
3. [Understanding the Validation Error](#understanding-the-validation-error)
4. [Potential Issues and Solutions](#potential-issues-and-solutions)
    - [A. Email Validation Failure](#a-email-validation-failure)
    - [B. Redundant Validation Annotations](#b-redundant-validation-annotations)
    - [C. JSON Deserialization Issues](#c-json-deserialization-issues)
    - [D. Exception Handling and Feedback](#d-exception-handling-and-feedback)
5. [Best Practices and Recommendations](#best-practices-and-recommendations)
    - [1. Streamline Validation Annotations](#1-streamline-validation-annotations)
    - [2. Enhance Exception Handling](#2-enhance-exception-handling)
    - [3. Improve Logging Mechanisms](#3-improve-logging-mechanisms)
    - [4. Strengthen Client-Side Validations](#4-strengthen-client-side-validations)
    - [5. Utilize API Documentation Tools](#5-utilize-api-documentation-tools)
    - [6. Secure Password Handling](#6-secure-password-handling)
6. [Comprehensive Example Implementation](#comprehensive-example-implementation)
    - [A. Revised `RegistrationRequest` Class](#a-revised-registrationrequest-class)
    - [B. Updated `AuthenticationController` Class](#b-updated-authenticationcontroller-class)
    - [C. Enhanced `AuthenticationService` Class](#c-enhanced-authenticationservice-class)
    - [D. Improved `GlobalExceptionHandler` Class](#d-improved-globalexceptionhandler-class)
7. [Testing the Registration Flow](#testing-the-registration-flow)
    - [1. Valid Registration Request](#1-valid-registration-request)
    - [2. Invalid Email Format](#2-invalid-email-format)
    - [3. Missing Email Field](#3-missing-email-field)
8. [Conclusion](#conclusion)
9. [Additional Resources](#additional-resources)

---

## 📋 **1. Application Overview**

Your Spring Boot application facilitates user registration with email activation. The primary components involved in this process include:

- **Controllers:** Handle HTTP requests related to authentication (e.g., registration).
- **Services:** Contain business logic for registering users and sending activation emails.
- **Repositories:** Interact with the database to perform CRUD operations on entities like `User`, `Role`, and `Token`.
- **Email Templates:** Define the structure of activation emails sent to users.
- **Configuration Files:** Manage application settings, such as server ports and mailing configurations.

Understanding how these components interact is crucial for identifying and resolving the validation errors you're encountering.

---

## 🔍 **2. Detailed Code Analysis**

Let's examine each of your provided code snippets to understand their roles and interconnections.

### 1. `EmailService` Class

```java
package com.wchamara.book.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(
            String to,
            String username,
            EmailTemplateName emailTemplateName,
            String confirmationUrl,
            String activationCode,
            String subject

    ) throws MessagingException {
        String templateName;

        if (emailTemplateName == null) {
            templateName = "confirm_email";
        } else {
            templateName = emailTemplateName.getName();
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                MULTIPART_MODE_MIXED,
                UTF_8.name()
        );

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activation_code", activationCode);

        Context context = new Context();
        context.setVariables(properties);

        helper.setFrom("ccweerasinghe1994@gmail.com");
        helper.setSubject(subject);
        helper.setTo(to);

        String template = templateEngine.process(templateName, context);

        helper.setText(template, true);

        mailSender.send(mimeMessage);
    }
}
```

**🔑 Key Points:**

- **Purpose:** Sends emails using Thymeleaf templates.
- **Dependencies:**
  - `JavaMailSender`: For sending emails.
  - `SpringTemplateEngine`: For processing Thymeleaf templates.
- **`@Async` Annotation:** Enables asynchronous execution, allowing email sending to occur in the background without blocking the main thread.
- **Method Parameters:**
  - `to`: Recipient's email address.
  - `username`: Recipient's name for personalization.
  - `emailTemplateName`: Enum specifying the email template to use.
  - `confirmationUrl`: URL for account activation.
  - `activationCode`: Code for activating the account.
  - `subject`: Email subject line.

**🔄 Flow:**

1. **Template Selection:** Chooses the email template based on `emailTemplateName`. Defaults to `"confirm_email"` if none is provided.
2. **MimeMessage Creation:** Constructs the email message.
3. **Context Population:** Injects variables (`username`, `confirmationUrl`, `activation_code`) into the Thymeleaf context.
4. **Email Composition:**
   - Sets the sender's email.
   - Sets the email subject.
   - Sets the recipient's email.
   - Processes the Thymeleaf template with the provided context.
   - Sets the email content as HTML.
5. **Email Sending:** Dispatches the composed email.

**🛠️ Example Usage:**

Assuming you want to send an activation email to a user named "John Doe" with a confirmation URL and activation code:

```java
emailService.sendEmail(
    "john.doe@example.com",
    "John Doe",
    EmailTemplateName.ACTIVATE_ACCOUNT,
    "http://localhost:4200/activate-account",
    "123456",
    "Account Activation"
);
```

---

### 2. `EmailTemplateName` Enum

```java
package com.wchamara.book.email;

import lombok.Getter;

@Getter
public enum EmailTemplateName {

    ACTIVATE_ACCOUNT("activate_account");

    private final String name;

    EmailTemplateName(String name) {
        this.name = name;
    }
}
```

**🔑 Key Points:**

- **Purpose:** Enumerates the different email templates available.
- **Current Value:** `ACTIVATE_ACCOUNT` mapped to `"activate_account"`.
- **`@Getter` Annotation:** Generates a getter for the `name` field.

**🛠️ Example Usage:**

When sending an activation email, you use the `ACTIVATE_ACCOUNT` enum to specify the template:

```java
emailService.sendEmail(
    user.getEmail(),
    user.getFullName(),
    EmailTemplateName.ACTIVATE_ACCOUNT,
    activationUrl,
    token,
    "Account Activation"
);
```

---

### 3. `AuthenticationController` Class

```java
package com.wchamara.book.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {

        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }
}
```

**🔑 Key Points:**

- **Purpose:** Handles HTTP requests related to authentication, specifically user registration.
- **Annotations:**
  - `@RestController`: Marks the class as a RESTful controller.
  - `@RequestMapping("auth")`: Base path for all endpoints in this controller.
  - `@RequiredArgsConstructor`: Generates a constructor with required arguments (i.e., `final` fields).
  - `@Tag(name = "Authentication")`: Swagger annotation for API documentation.
- **Dependency:**
  - `AuthenticationService`: Handles the business logic for user registration.
- **Endpoint:**
  - **Path:** `POST /auth/register`
  - **Functionality:**
    - Accepts a `RegistrationRequest` payload.
    - Validates the request using `@Valid`.
    - Invokes the `register` method in `AuthenticationService`.
    - Returns an HTTP 202 (Accepted) status.

**🛠️ Example Request:**

```http
POST /auth/register HTTP/1.1
Content-Type: application/json

{
  "firstname": "John",
  "lastname": "Doe",
  "email": "john.doe@example.com",
  "password": "SecurePassword123"
}
```

**🛠️ Example Response:**

```http
HTTP/1.1 202 Accepted
Content-Type: application/json
```

*Note: The response body is empty as per the controller's implementation.*

---

### 4. `AuthenticationService` Class

```java
package com.wchamara.book.auth;

import com.wchamara.book.email.EmailService;
import com.wchamara.book.email.EmailTemplateName;
import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;

    @Value("${application.mailing.frontend}")
    private String activationUrl;


    public void register(RegistrationRequest request) throws MessagingException {
        Role userRole = roleRepository.findByName("USER").orElseThrow(
                () -> new IllegalStateException("Role user was not initialized")
        );

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var token = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                token,
                "Account Activation"
        );
        
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);

        Token token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;

    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(characters.length());
            char charAt = characters.charAt(index);
            codeBuilder.append(charAt);
        }

        return codeBuilder.toString();
    }
}
```

**🔑 Key Points:**

- **Purpose:** Handles the business logic for user registration, including saving the user, generating activation tokens, and sending activation emails.
- **Annotations:**
  - `@Service`: Marks the class as a service component in Spring.
  - `@RequiredArgsConstructor`: Generates a constructor with required arguments.
- **Dependencies:**
  - `RoleRepository`: For fetching user roles from the database.
  - `PasswordEncoder`: For hashing user passwords.
  - `UserRepository`: For CRUD operations on `User` entities.
  - `TokenRepository`: For managing activation tokens.
  - `EmailService`: For sending activation emails.
- **Configuration:**
  - `@Value("${application.mailing.frontend}")`: Injects the `activationUrl` from `application.yml`.
- **Methods:**
  - `register`: Main method to handle user registration.
  - `sendValidationEmail`: Sends the activation email to the user.
  - `generateAndSaveActivationToken`: Creates and saves an activation token.
  - `generateActivationCode`: Generates a random numeric activation code of specified length.

**🔄 Flow:**

1. **Role Retrieval:** Fetches the "USER" role from the database.
2. **User Creation:** Builds a `User` object with provided details and hashed password.
3. **User Saving:** Persists the `User` object to the database.
4. **Activation Token Generation:** Creates a 6-digit activation code and saves it with an expiration time.
5. **Email Dispatch:** Sends an activation email to the user with the activation code and confirmation URL.

**🛠️ Example Flow:**

1. **User Registration Request:**

   ```json
   {
     "firstname": "John",
     "lastname": "Doe",
     "email": "john.doe@example.com",
     "password": "SecurePassword123"
   }
   ```

2. **Service Processing:**

   - Retrieves the "USER" role.
   - Hashes the password.
   - Saves the user to the database.
   - Generates a 6-digit activation code (e.g., "482950").
   - Saves the activation token with an expiration time (15 minutes from creation).
   - Sends an activation email to `john.doe@example.com` with the activation code and confirmation URL.

---

### 5. `BookNetworkApplication` Class

```java
package com.wchamara.book;

import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class BookNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookNetworkApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {

            if (roleRepository.findByName("USER").isEmpty()) {
                roleRepository.save(Role.builder()
                        .name("USER")
                        .build());
            }

        };
    }

}
```

**🔑 Key Points:**

- **Purpose:** Entry point of the Spring Boot application.
- **Annotations:**
  - `@SpringBootApplication`: Indicates a Spring Boot application.
  - `@EnableJpaAuditing`: Enables JPA auditing features.
  - `@EnableAsync`: Enables asynchronous method execution (required for `@Async` in `EmailService`).
- **`CommandLineRunner` Bean:**
  - **Purpose:** Initializes the database with the "USER" role if it doesn't already exist.
  - **Flow:**
    - Checks if the "USER" role exists.
    - If absent, creates and saves it to the database.

**🛠️ Example Scenario:**

Upon starting the application, the `CommandLineRunner` ensures that there's at least one role ("USER") in the `RoleRepository`. This setup is crucial for assigning roles to users during registration.

---

### 6. `activate_account.html` Template

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Account Activation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            max-width: 600px;
            margin: 10px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .activation-code {
            font-size: 36px;
            text-align: center;
            margin-bottom: 20px;
        }

        .activation-link {
            display: block;
            text-align: center;
            margin-top: 20px;
        }

        .activation-link a {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Account Activation</h1>
    <p class="greeting" th:text="'Hello ' + ${username} + ','"></p>
    <p>Thank you for signing up! Please use the following activation code to activate your account:</p>
    <div class="activation-code"><span th:text="${activation_code}"></span></div>
    <div class="activation-link">
        <a target="_blank" th:href="${confirmationUrl}">Activate your account</a>
    </div>
</div>
</body>
</html>
```

**🔑 Key Points:**

- **Purpose:** Thymeleaf template for the account activation email.
- **Structure:**
  - **Styling:** Inline CSS for basic styling of the email.
  - **Content:**
    - Greeting with the user's name.
    - Activation code displayed prominently.
    - Activation link button directing to the frontend's activation page.
- **Thymeleaf Attributes:**
  - `th:text`: Replaces the element's text content with the provided value.
  - `th:href`: Sets the `href` attribute dynamically based on the provided URL.

**🛠️ Example Rendered Email:**

When processed with the following context:

```java
Map<String, Object> properties = new HashMap<>();
properties.put("username", "John Doe");
properties.put("confirmationUrl", "http://localhost:4200/activate-account");
properties.put("activation_code", "123456");
```

**Rendered Email Content:**

---

**Subject:** Account Activation

---

**Body:**

```
Hello John Doe,

Thank you for signing up! Please use the following activation code to activate your account:

123456

[Activate your account](http://localhost:4200/activate-account)
```

---

### 7. `application.yml` Configuration

```yaml
mailing:
  frontend: http://localhost:4200/activate-account

server:
  port: 8088
```

**🔑 Key Points:**

- **Purpose:** Configures application-specific properties.
- **Properties:**
  - `mailing.frontend`: URL pointing to the frontend's account activation page.
  - `server.port`: Specifies that the application runs on port `8088`.

**🛠️ Example Usage:**

- **Activation URL:** Utilized in the `AuthenticationService` to provide users with a link to activate their accounts.

---

## 🧐 **3. Understanding the Validation Error**

**Latest Log Entry:**

```
2024-09-19T15:43:27.446+05:30  WARN 26968 --- [nio-8088-exec-7] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in public org.springframework.http.ResponseEntity<?> com.wchamara.book.auth.AuthenticationController.register(com.wchamara.book.auth.RegistrationRequest) throws jakarta.mail.MessagingException: [Field error in object 'registrationRequest' on field 'email': rejected value [ccweerasinghe1994]; codes [Email.registrationRequest.email,Email.email,Email.java.lang.String,Email]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [registrationRequest.email,email]; arguments []; default message [email],[Ljakarta.validation.constraints.Pattern$Flag;@661689e5,.*]; default message [Invalid email]] ]
```

**🔑 Key Points:**

- **Exception Type:** `MethodArgumentNotValidException`
- **Affected Argument:** The first argument (`[0]`) in the `register` method of `AuthenticationController`.
- **Field with Error:** `email`
- **Rejected Value:** `ccweerasinghe1994`
- **Validation Error Message:** `Invalid email`
- **Validation Constraint Triggered:** `@Email`

**🔍 Interpretation:**

The `email` field in the `RegistrationRequest` object received the value `ccweerasinghe1994`, which fails the `@Email` validation constraint. The `@Email` annotation ensures that the provided string adheres to a valid email format (e.g., contains an `@` symbol and a domain).

**📈 Impact:**

- **User Experience:** Users attempting to register with invalid email formats receive clear error messages indicating the issue.
- **Data Integrity:** Prevents the creation of user accounts with malformed email addresses, ensuring reliable communication channels.

---

## ⚠️ **4. Potential Issues and Solutions**

Given the current validation error related to the `email` field, let's explore potential causes and provide solutions.

### A. **Email Validation Failure**

**Cause:**

The email `ccweerasinghe1994` lacks essential components of a valid email address, such as the `@` symbol and domain (e.g., `.com`, `.org`). The `@Email` annotation in your `RegistrationRequest` class is correctly identifying this as an invalid email format.

**Solution:**

1. **Client-Side Validation:**

   - **Purpose:** Prevent users from submitting invalid email formats, enhancing user experience and reducing server-side validation errors.
   - **Implementation:**
     - **HTML5 Validation:**
       ```html
       <form name="registrationForm" onsubmit="return validateForm()">
         <input type="text" name="firstname" required>
         <input type="text" name="lastname" required>
         <input type="email" name="email" required>
         <input type="password" name="password" required>
         <button type="submit">Register</button>
       </form>
       ```
       - **Explanation:** The `type="email"` attribute ensures that browsers perform basic email format validation.

     - **JavaScript Validation:**
       ```javascript
       function validateForm() {
           const email = document.forms["registrationForm"]["email"].value;
           const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
           if (!emailPattern.test(email)) {
               alert("Please enter a valid email address.");
               return false;
           }
           // Additional validations...
           return true;
       }
       ```
       - **Explanation:** This script uses a regex pattern to validate the email format before allowing form submission.

2. **Server-Side Validation Enhancements:**

   - **Purpose:** Ensure data integrity and security by validating inputs on the server, regardless of client-side validations.
   - **Implementation:**
     - **Maintain `@Email` Annotation:** Continue using `@Email` in your `RegistrationRequest` class to enforce valid email formats.
     - **Provide Clear Error Messages:** Ensure that the `GlobalExceptionHandler` returns informative messages to guide users in correcting their inputs.

3. **Feedback Mechanism:**

   - **Purpose:** Inform users promptly about validation errors, enabling them to rectify issues without frustration.
   - **Implementation:**
     - **Frontend Integration:** Design the frontend to display error messages received from the server, highlighting specific fields that require attention.
     - **Example:**
       ```javascript
       // Example using Fetch API
       fetch('http://localhost:8088/auth/register', {
           method: 'POST',
           headers: {
               'Content-Type': 'application/json'
           },
           body: JSON.stringify({
               firstname: 'John',
               lastname: 'Doe',
               email: 'johndoe', // Invalid email
               password: 'SecurePassword123'
           })
       })
       .then(response => response.json())
       .then(data => {
           if (response.status === 400) {
               // Display error message to the user
               alert(data.email); // "Invalid email"
           } else {
               // Proceed with success flow
               alert('Registration successful!');
           }
       })
       .catch(error => console.error('Error:', error));
       ```

### B. **Redundant Validation Annotations**

**Cause:**

In earlier interactions, there were validation errors related to the `firstname` field, where both `@NotBlank` and `@NotEmpty` annotations were applied. This redundancy led to multiple error messages for the same validation failure.

**Solution:**

1. **Streamline Validation Annotations:**

   - **Use `@NotBlank` Alone for String Fields:**
     - **Reason:** `@NotBlank` ensures that the string is not `null`, not empty, and contains at least one non-whitespace character.
     - **Remove `@NotEmpty`:** Since `@NotBlank` already covers non-emptiness, `@NotEmpty` becomes redundant.
   - **Revised `RegistrationRequest` Example:**
     ```java
     import javax.validation.constraints.Email;
     import javax.validation.constraints.NotBlank;
     import com.fasterxml.jackson.annotation.JsonProperty;
     import lombok.Data;

     @Data
     public class RegistrationRequest {
         
         @NotBlank(message = "First name cannot be empty")
         @JsonProperty("firstname")
         private String firstname;
         
         @NotBlank(message = "Last name cannot be empty")
         @JsonProperty("lastname")
         private String lastname;
         
         @Email(message = "Invalid email")
         @NotBlank(message = "Email cannot be empty")
         @JsonProperty("email")
         private String email;
         
         @NotBlank(message = "Password cannot be empty")
         @JsonProperty("password")
         private String password;
     }
     ```
   
2. **Benefits:**

   - **Clarity:** Eliminates multiple error messages for the same validation issue.
   - **Maintainability:** Reduces complexity in validation annotations, making the codebase easier to manage.

### C. **JSON Deserialization Issues**

**Cause:**

Mismatch between JSON keys and Java class field names can lead to fields not being populated correctly, resulting in `null` values and subsequent validation failures.

**Solution:**

1. **Ensure Consistent Naming:**

   - **Match JSON Keys with Java Fields:**
     - **Example:** If the Java field is `firstname`, the JSON key should also be `"firstname"`.
     - **Incorrect Example:** Java field `firstname` vs. JSON key `"firstName"`.
   - **Use `@JsonProperty` for Mapping:**
     - **Purpose:** Maps JSON keys to Java fields when naming conventions differ.
     - **Example:**
       ```java
       @NotBlank(message = "First name cannot be empty")
       @JsonProperty("firstName")
       private String firstname;
       ```

2. **Global Naming Strategies (Optional):**

   - **Purpose:** Applies a naming convention across all JSON-serialized/deserialized fields.
   - **Implementation:**
     - **Configure in `application.yml`:**
       ```yaml
       spring:
         jackson:
           property-naming-strategy: SNAKE_CASE
       ```
     - **Effect:** Translates Java's `firstName` to JSON's `first_name` automatically.
   - **Note:** Ensure that frontend and backend agree on the naming conventions to prevent deserialization issues.

3. **Utilize Lombok for Getters and Setters:**

   - **Purpose:** Facilitates proper serialization/deserialization by ensuring all fields have appropriate accessors.
   - **Implementation:**
     - **Use `@Data` Annotation:**
       ```java
       import lombok.Data;

       @Data
       public class RegistrationRequest {
           // Fields with annotations
       }
       ```
     - **Benefit:** Automatically generates getters and setters, ensuring Jackson can access and populate fields correctly.

4. **Debugging Tips:**

   - **Enable Detailed Logging:**
     - **Purpose:** Helps identify how incoming JSON payloads are being deserialized.
     - **Implementation:**
       - **Set Logging Level to DEBUG:**
         ```properties
         logging.level.org.springframework.web=DEBUG
         logging.level.com.wchamara.book=DEBUG
         ```
       - **Benefit:** Logs detailed information about request handling and deserialization.

   - **Inspect Deserialized Objects:**
     - **Use Breakpoints:** In your controller method to inspect the state of the `RegistrationRequest` object after deserialization.
     - **Verify Field Values:** Ensure that all expected fields are populated correctly.

### D. **Exception Handling and Feedback**

**Cause:**

Without proper exception handling, clients may receive generic error messages that are not informative, making it difficult for them to understand and rectify input issues.

**Solution:**

1. **Implement a Global Exception Handler:**

   - **Purpose:** Catches and processes exceptions uniformly across the application, providing structured and meaningful error responses.
   - **Implementation:**
     ```java
     package com.wchamara.book.auth;

     import org.springframework.http.HttpStatus;
     import org.springframework.http.ResponseEntity;
     import org.springframework.validation.FieldError;
     import org.springframework.web.bind.MethodArgumentNotValidException;
     import org.springframework.web.bind.annotation.ControllerAdvice;
     import org.springframework.web.bind.annotation.ExceptionHandler;

     import java.util.HashMap;
     import java.util.Map;

     @ControllerAdvice
     public class GlobalExceptionHandler {
         
         @ExceptionHandler(MethodArgumentNotValidException.class)
         public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
             Map<String, String> errors = new HashMap<>();
             ex.getBindingResult().getAllErrors().forEach((error) -> {
                 String fieldName = ((FieldError) error).getField();
                 String errorMessage = error.getDefaultMessage();
                 errors.put(fieldName, errorMessage);
             });
             return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
         }
         
         // Additional exception handlers can be added here
     }
     ```

   - **Benefits:**
     - **Clarity:** Provides clients with specific error messages tied to particular fields.
     - **Consistency:** Ensures that all validation errors are handled uniformly.

2. **Enhance Error Messages:**

   - **Provide Contextual Information:** Ensure that error messages clearly indicate what went wrong and how to fix it.
   - **Example Error Response:**
     ```json
     {
       "email": "Invalid email",
       "firstname": "First name cannot be empty"
     }
     ```

3. **Frontend Integration:**

   - **Display Errors Appropriately:** Design the frontend to parse and display error messages in a user-friendly manner.
   - **Example:**
     ```javascript
     fetch('http://localhost:8088/auth/register', {
         method: 'POST',
         headers: {
             'Content-Type': 'application/json'
         },
         body: JSON.stringify({
             firstname: 'John',
             lastname: 'Doe',
             email: 'johndoe', // Invalid email
             password: 'SecurePassword123'
         })
     })
     .then(response => {
         if (!response.ok) {
             return response.json().then(data => { throw data; });
         }
         return response.json();
     })
     .then(data => {
         alert('Registration successful!');
     })
     .catch(error => {
         for (const field in error) {
             console.error(`${field}: ${error[field]}`);
             // Display error messages next to form fields
         }
     });
     ```

---

## 💡 **5. Best Practices and Recommendations**

To enhance your application's robustness, user experience, and security, consider implementing the following best practices.

### 1. **Streamline Validation Annotations**

- **Use Appropriate Annotations:**
  - **`@NotBlank` vs. `@NotEmpty`:**
    - **`@NotBlank`:** Validates that the string is not `null`, not empty, and contains at least one non-whitespace character.
    - **`@NotEmpty`:** Validates that the string is not `null` and not empty (does not check for whitespace).
  - **Recommendation:** Use `@NotBlank` for string fields to ensure comprehensive validation.

- **Avoid Redundancy:**
  - **Issue:** Applying both `@NotBlank` and `@NotEmpty` on the same field leads to multiple error messages for a single validation failure.
  - **Solution:** Retain only the most appropriate annotation based on the validation requirements.

### 2. **Enhance Exception Handling**

- **Implement Comprehensive Exception Handlers:**
  - **Purpose:** To catch various exceptions (e.g., `MethodArgumentNotValidException`, `DataIntegrityViolationException`) and provide meaningful responses.
  - **Example:**
    ```java
    @ControllerAdvice
    public class GlobalExceptionHandler {
        
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        
        @ExceptionHandler(DataIntegrityViolationException.class)
        public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
            // Log the exception details for debugging
            // Return a user-friendly message
            return new ResponseEntity<>("Data integrity violation occurred.", HttpStatus.CONFLICT);
        }
        
        // Additional exception handlers...
    }
    ```

- **Benefits:**
  - **User Guidance:** Helps users understand what went wrong and how to fix it.
  - **Developer Insights:** Provides developers with detailed logs for debugging.

### 3. **Improve Logging Mechanisms**

- **Enable Detailed Logging:**
  - **Purpose:** To gain insights into application behavior and identify issues promptly.
  - **Implementation:**
    ```properties
    # application.properties
    logging.level.org.springframework.web=DEBUG
    logging.level.com.wchamara.book=DEBUG
    ```

- **Log Important Events:**
  - **Example:** Log when an email is sent or when a user is successfully registered.
    ```java
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    @Service
    @RequiredArgsConstructor
    public class EmailService {
        private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
        // Existing code...

        @Async
        public void sendEmail(...) throws MessagingException {
            // Existing code...
            logger.info("Sending email to {}", to);
            mailSender.send(mimeMessage);
            logger.info("Email sent successfully to {}", to);
        }
    }
    ```

- **Benefits:**
  - **Debugging:** Facilitates tracking the flow of data and identifying bottlenecks or failures.
  - **Monitoring:** Assists in monitoring application health and performance.

### 4. **Strengthen Client-Side Validations**

- **Purpose:** To provide immediate feedback to users, enhancing user experience and reducing server load.
- **Implementation:**
  - **HTML5 Validations:** Utilize form input types and attributes for basic validations.
  - **JavaScript Validations:** Implement advanced validations using JavaScript or frontend frameworks (e.g., React, Angular).

- **Example:**
  ```html
  <form id="registrationForm">
      <input type="text" name="firstname" required placeholder="First Name">
      <input type="text" name="lastname" required placeholder="Last Name">
      <input type="email" name="email" required placeholder="Email Address">
      <input type="password" name="password" required placeholder="Password">
      <button type="submit">Register</button>
  </form>

  <script>
      document.getElementById('registrationForm').addEventListener('submit', function(event) {
          const email = this.email.value;
          const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
          if (!emailPattern.test(email)) {
              alert('Please enter a valid email address.');
              event.preventDefault();
          }
          // Additional validations...
      });
  </script>
  ```

### 5. **Utilize API Documentation Tools**

- **Purpose:** To provide clear and interactive API documentation, facilitating easier integration for frontend developers and third-party clients.
- **Implementation with Springdoc OpenAPI:**
  - **Add Dependency (Maven):**
    ```xml
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-ui</artifactId>
        <version>1.6.14</version>
    </dependency>
    ```
  
  - **Access Swagger UI:**
    - Typically available at `http://localhost:8088/swagger-ui.html` or `http://localhost:8088/swagger-ui/index.html`.

- **Benefits:**
  - **Interactivity:** Allows developers to test API endpoints directly from the browser.
  - **Clarity:** Auto-generates documentation based on controllers and models, ensuring accuracy.

### 6. **Secure Password Handling**

- **Purpose:** To protect user credentials and prevent unauthorized access.
- **Implementation:**
  - **Password Encoding:** Utilize `PasswordEncoder` (e.g., BCrypt) to hash passwords before storing them.
    ```java
    @Service
    @RequiredArgsConstructor
    public class AuthenticationService {
        private final PasswordEncoder passwordEncoder;
        // Existing code...

        public void register(RegistrationRequest request) {
            // Existing code...
            User user = User.builder()
                    .password(passwordEncoder.encode(request.getPassword()))
                    // Other fields...
                    .build();
            // Existing code...
        }
    }
    ```

  - **Password Strength Validation:** Enforce strong password policies using regex patterns.
    ```java
    import javax.validation.constraints.Pattern;

    public class RegistrationRequest {
        
        // Existing fields...

        @NotBlank(message = "Password cannot be empty")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
                 message = "Password must be minimum eight characters, at least one letter and one number")
        @JsonProperty("password")
        private String password;
    }
    ```

- **Benefits:**
  - **Security:** Protects user data from breaches and unauthorized access.
  - **Compliance:** Meets industry standards for password security.

---

## 🚀 **6. Comprehensive Example Implementation**

To address the current validation issue and enhance your application, let's implement the recommended changes step-by-step.

### A. **Revised `RegistrationRequest` Class**

```java
package com.wchamara.book.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegistrationRequest {
    
    @NotBlank(message = "First name cannot be empty")
    @JsonProperty("firstname")
    private String firstname;
    
    @NotBlank(message = "Last name cannot be empty")
    @JsonProperty("lastname")
    private String lastname;
    
    @Email(message = "Invalid email")
    @NotBlank(message = "Email cannot be empty")
    @JsonProperty("email")
    private String email;
    
    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
             message = "Password must be minimum eight characters, at least one letter and one number")
    @JsonProperty("password")
    private String password;
}
```

**🔑 Enhancements:**

- **Password Strength Validation:** Ensures that passwords are at least eight characters long and contain both letters and numbers.
- **Consistent Field Mapping:** Uses `@JsonProperty` to align JSON keys with Java fields.
- **Lombok's `@Data` Annotation:** Generates getters, setters, `toString`, `equals`, and `hashCode` methods, reducing boilerplate code.

### B. **Updated `AuthenticationController` Class**

```java
package com.wchamara.book.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }
}
```

**🔑 Enhancements:**

- **Removal of `@ResponseStatus`:** Relying on `ResponseEntity` to set the HTTP status, providing more flexibility.
- **Consistent Exception Handling:** Delegates exception handling to the `GlobalExceptionHandler`.

### C. **Enhanced `AuthenticationService` Class**

```java
package com.wchamara.book.auth;

import com.wchamara.book.email.EmailService;
import com.wchamara.book.email.EmailTemplateName;
import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;

    @Value("${mailing.frontend}")
    private String activationUrl; // Updated to match application.yml

    public void register(RegistrationRequest request) throws MessagingException {
        Role userRole = roleRepository.findByName("USER").orElseThrow(
                () -> new IllegalStateException("Role 'USER' was not initialized")
        );

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var token = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                token,
                "Account Activation"
        );
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);

        Token token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(characters.length());
            char charAt = characters.charAt(index);
            codeBuilder.append(charAt);
        }

        return codeBuilder.toString();
    }
}
```

**🔑 Enhancements:**

- **Configuration Property Correction:**
  - **Previous:** `@Value("${application.mailing.frontend}")`
  - **Updated:** `@Value("${mailing.frontend}")` to match the `application.yml` configuration.
- **Consistent Error Messages:** Enhanced the exception message for role initialization.

### D. **Improved `GlobalExceptionHandler` Class**

```java
package com.wchamara.book.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    // Additional exception handlers can be added here
}
```

**🔑 Enhancements:**

- **Structured Error Responses:** Converts validation errors into a JSON map, mapping field names to their respective error messages.
- **Flexibility for Additional Exceptions:** Placeholder for handling other types of exceptions.

**🛠️ Example Error Response:**

```json
{
  "email": "Invalid email",
  "firstname": "First name cannot be empty"
}
```

---

## 🔍 **7. Testing the Registration Flow**

To ensure that the registration and email activation process works as intended, perform thorough testing using API tools like **Postman**, **Insomnia**, or **cURL**.

### 1. **Valid Registration Request**

**Request Payload:**

```json
{
  "firstname": "Alice",
  "lastname": "Smith",
  "email": "alice.smith@example.com",
  "password": "StrongPassword123"
}
```

**Expected Outcome:**

- **HTTP Status:** `202 Accepted`
- **Response Body:** Empty (as per the controller's implementation).
- **Email Sent:** An activation email to `alice.smith@example.com` containing the activation code and activation link.

**Verification Steps:**

1. **Send the Request:**
   ```bash
   curl -X POST http://localhost:8088/auth/register \
   -H "Content-Type: application/json" \
   -d '{
         "firstname": "Alice",
         "lastname": "Smith",
         "email": "alice.smith@example.com",
         "password": "StrongPassword123"
       }'
   ```

2. **Check the Response:**
   - Ensure that the response status is `202 Accepted`.
   
3. **Verify Email Receipt:**
   - Check the recipient's inbox for the activation email.
   - Confirm that the activation code and link are present and correctly formatted.

### 2. **Invalid Email Format**

**Request Payload:**

```json
{
  "firstname": "Bob",
  "lastname": "Johnson",
  "email": "bobjohnson", // Invalid email
  "password": "SecurePass456"
}
```

**Expected Outcome:**

- **HTTP Status:** `400 Bad Request`
- **Response Body:**
  ```json
  {
    "email": "Invalid email"
  }
  ```

**Verification Steps:**

1. **Send the Request:**
   ```bash
   curl -X POST http://localhost:8088/auth/register \
   -H "Content-Type: application/json" \
   -d '{
         "firstname": "Bob",
         "lastname": "Johnson",
         "email": "bobjohnson",
         "password": "SecurePass456"
       }'
   ```

2. **Check the Response:**
   - Ensure that the response status is `400 Bad Request`.
   - Verify that the response body contains the error message for the `email` field.

### 3. **Missing Email Field**

**Request Payload:**

```json
{
  "firstname": "Charlie",
  "lastname": "Brown",
  "password": "AnotherPass789"
}
```

**Expected Outcome:**

- **HTTP Status:** `400 Bad Request`
- **Response Body:**
  ```json
  {
    "email": "Email cannot be empty"
  }
  ```

**Verification Steps:**

1. **Send the Request:**
   ```bash
   curl -X POST http://localhost:8088/auth/register \
   -H "Content-Type: application/json" \
   -d '{
         "firstname": "Charlie",
         "lastname": "Brown",
         "password": "AnotherPass789"
       }'
   ```

2. **Check the Response:**
   - Ensure that the response status is `400 Bad Request`.
   - Verify that the response body indicates that the `email` field cannot be empty.

### 4. **Empty Email Field**

**Request Payload:**

```json
{
  "firstname": "Diana",
  "lastname": "Prince",
  "email": "",
  "password": "WonderWoman123"
}
```

**Expected Outcome:**

- **HTTP Status:** `400 Bad Request`
- **Response Body:**
  ```json
  {
    "email": "Email cannot be empty"
  }
  ```

**Verification Steps:**

1. **Send the Request:**
   ```bash
   curl -X POST http://localhost:8088/auth/register \
   -H "Content-Type: application/json" \
   -d '{
         "firstname": "Diana",
         "lastname": "Prince",
         "email": "",
         "password": "WonderWoman123"
       }'
   ```

2. **Check the Response:**
   - Ensure that the response status is `400 Bad Request`.
   - Verify that the response body indicates that the `email` field cannot be empty.

---

## 📈 **8. Conclusion**

Your Spring Boot application's registration and email activation process is well-structured, leveraging Spring's robust features such as validation, asynchronous processing, and template-based email generation. However, the validation errors you're encountering, particularly with the `email` field, stem from invalid input formats and potential misconfigurations in request handling.

By implementing the recommended solutions—streamlining validation annotations, ensuring consistent JSON field mappings, enhancing exception handling, and strengthening client-side validations—you can effectively resolve these issues. Additionally, adopting best practices in logging, API documentation, and security will further fortify your application, ensuring a seamless and secure user experience.

---

## 📚 **9. Additional Resources**

- **Spring Boot Validation:**
  - [Spring Boot Validation Guide](https://spring.io/guides/gs/validating-form-input/)
  
- **Jackson Annotations:**
  - [Jackson Annotations Reference](https://github.com/FasterXML/jackson-annotations)
  
- **Project Lombok:**
  - [Project Lombok Official Site](https://projectlombok.org/)
  
- **Springdoc OpenAPI:**
  - [Springdoc OpenAPI Documentation](https://springdoc.org/)
  
- **Thymeleaf Documentation:**
  - [Thymeleaf Spring Boot Integration](https://www.thymeleaf.org/documentation.html#springboot)
  
- **JavaMailSender Documentation:**
  - [JavaMailSender Interface](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/mail/javamail/JavaMailSender.html)
  
- **PasswordEncoder Interface:**
  - [PasswordEncoder Documentation](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/password/PasswordEncoder.html)
  
- **Spring Boot Logging:**
  - [Spring Boot Logging Configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.logging)
  
- **Testing Spring Boot Applications:**
  - [Testing Spring Boot](https://spring.io/guides/gs/testing-web/)

---

Feel free to reach out with further questions or updates on your progress! Happy coding! 🚀

# Implemneting /authenticate endpoint


```java
package com.wchamara.book.auth;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {

        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}

```

```java
package com.wchamara.book.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {
    @NotEmpty(message = "Email is required")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}

```

```java
package com.wchamara.book.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationResponse {

    private String token;
    
}

```

```java
package com.wchamara.book.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

```

```java
package com.wchamara.book.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.expiration}")
    private Long jwtExpirationInMs;

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);


    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSignedKey()).build().parseSignedClaims(token).getPayload();
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {

        return buildToke(claims, userDetails, jwtExpirationInMs);
    }

    private String buildToke(Map<String, Object> extraClaims, UserDetails userDetails, Long jwtExpirationInMs) {
        var authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .claim("authorities", authorities)
                .signWith(getSignedKey())
                .compact();

    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private SecretKey getSignedKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

```

```java
package com.wchamara.book.auth;

import com.wchamara.book.email.EmailService;
import com.wchamara.book.email.EmailTemplateName;
import com.wchamara.book.role.Role;
import com.wchamara.book.role.RoleRepository;
import com.wchamara.book.security.JwtService;
import com.wchamara.book.user.Token;
import com.wchamara.book.user.TokenRepository;
import com.wchamara.book.user.User;
import com.wchamara.book.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Value("${application.mailing.frontend}")
    private String activationUrl;


    public void register(RegistrationRequest request) throws MessagingException {
        Role userRole = roleRepository.findByName("USER").orElseThrow(
                () -> new IllegalStateException("Role user was not initialized")
        );

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var token = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                token,
                "Account Activation"
        );

    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);

        Token token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;

    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(characters.length());
            char charAt = characters.charAt(index);
            codeBuilder.append(charAt);
        }

        return codeBuilder.toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        var claims = new HashMap<String, Object>();

        User user = (User) authenticate.getPrincipal();
        claims.put("fullName", user.getFullName());

        var jwtToken = jwtService.generateToken(claims, user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

```
Absolutely! Adding emojis can make the documentation more engaging and easier to navigate. Below is the revised explanation with relevant emojis added to the titles:

---

## 📋 1. **Overview**

The implementation comprises several components:

1. **Controllers and Endpoints**: Handle HTTP requests related to authentication.
2. **Data Transfer Objects (DTOs)**: Define the structure of request and response payloads.
3. **Configuration Classes**: Set up Spring Security and other necessary beans.
4. **Services**: Contain the business logic for registration, authentication, and JWT handling.
5. **Repositories and Entities**: Manage data persistence for users, roles, and tokens.

Let's delve into each component step-by-step.

---

## 🔐 2. **AuthenticationController**

### **Purpose**

The `AuthenticationController` serves as the entry point for authentication-related HTTP requests. It exposes two primary endpoints:

- **POST `/auth/register`**: For user registration.
- **POST `/auth/authenticate`**: For user login/authentication.

### **Code Breakdown**

```java
@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationRequest request) throws MessagingException {
        authenticationService.register(request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
```

### **Detailed Explanation**

- **Annotations**:
  - `@RestController`: Indicates that this class handles RESTful web services.
  - `@RequestMapping("auth")`: Sets the base URL path for the controller to `/auth`.
  - `@RequiredArgsConstructor`: Generates a constructor with required arguments (i.e., final fields).
  - `@Tag(name = "Authentication")`: For Swagger/OpenAPI documentation, categorizing the endpoints under "Authentication".

- **Endpoints**:
  - **`/register`**:
    - **Method**: `POST`
    - **Request Body**: `RegistrationRequest` (validated with `@Valid`)
    - **Response**: `202 Accepted` status with no body.
    - **Functionality**: Delegates the registration logic to `authenticationService.register(request)`.
  - **`/authenticate`**:
    - **Method**: `POST`
    - **Request Body**: `AuthenticationRequest` (validated with `@Valid`)
    - **Response**: `200 OK` with an `AuthenticationResponse` containing a JWT token.
    - **Functionality**: Delegates the authentication logic to `authenticationService.authenticate(request)`.

### **Example Usage**

1. **User Registration**:
   - **Request**:
     ```http
     POST /auth/register
     Content-Type: application/json

     {
       "firstname": "John",
       "lastname": "Doe",
       "email": "john.doe@example.com",
       "password": "SecurePass123"
     }
     ```
   - **Response**:
     ```
     HTTP/1.1 202 Accepted
     ```

2. **User Authentication**:
   - **Request**:
     ```http
     POST /auth/authenticate
     Content-Type: application/json

     {
       "email": "john.doe@example.com",
       "password": "SecurePass123"
     }
     ```
   - **Response**:
     ```json
     {
       "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
     }
     ```

---

## 📦 3. **Data Transfer Objects (DTOs)**

### a. 📝 **AuthenticationRequest**

```java
@Getter
@Setter
@Builder
public class AuthenticationRequest {
    @NotEmpty(message = "Email is required")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Password is required")
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
```

#### **Explanation**

- **Purpose**: Represents the structure of the authentication (login) request payload.
- **Validation Annotations**:
  - `@NotEmpty` & `@NotBlank`: Ensure that the fields are not empty or blank.
  - `@Email`: Validates the email format.
  - `@Size(min = 8)`: Ensures the password has a minimum length of 8 characters.
- **Lombok Annotations**:
  - `@Getter` & `@Setter`: Automatically generate getter and setter methods.
  - `@Builder`: Provides a builder pattern for creating instances.

### b. 📤 **AuthenticationResponse**

```java
@Getter
@Setter
@Builder
public class AuthenticationResponse {
    private String token;
}
```

#### **Explanation**

- **Purpose**: Represents the structure of the authentication response containing the JWT token.
- **Fields**:
  - `token`: The JWT token issued upon successful authentication.

### **Example**

- **Authentication Request**:
  ```json
  {
    "email": "jane.doe@example.com",
    "password": "StrongPass456"
  }
  ```

- **Authentication Response**:
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
  }
  ```

---

## ⚙️ 4. **Configuration Classes**

### **BeansConfig**

```java
@Configuration
@RequiredArgsConstructor
public class BeansConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

#### **Explanation**

- **Annotations**:
  - `@Configuration`: Indicates that this class contains bean definitions.
  - `@RequiredArgsConstructor`: Generates a constructor with required arguments (i.e., final fields).

- **Beans Defined**:
  - **`AuthenticationManager`**:
    - Obtained from `AuthenticationConfiguration`.
    - Manages authentication processes.
  - **`AuthenticationProvider`**:
    - Uses `DaoAuthenticationProvider` which retrieves user details from a `UserDetailsService`.
    - Sets the `UserDetailsService` and `PasswordEncoder` for handling authentication.
  - **`PasswordEncoder`**:
    - Uses `BCryptPasswordEncoder` for hashing passwords securely.

### **Example Usage**

- **Password Encoding**:
  ```java
  String rawPassword = "SecurePass123";
  String encodedPassword = passwordEncoder.encode(rawPassword);
  // Encoded password might look like: $2a$10$DowQ5LZ1V...
  ```

- **Authentication Manager**:
  - Utilized in `AuthenticationService` to authenticate user credentials.

---

## 🔑 5. **JWT Service**

### **JwtService**

```java
@Service
public class JwtService {

    @Value("${application.security.jwt.expiration}")
    private Long jwtExpirationInMs;

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSignedKey()).build().parseSignedClaims(token).getPayload();
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return buildToke(claims, userDetails, jwtExpirationInMs);
    }

    private String buildToke(Map<String, Object> extraClaims, UserDetails userDetails, Long jwtExpirationInMs) {
        var authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .claim("authorities", authorities)
                .signWith(getSignedKey())
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private SecretKey getSignedKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
```

#### **Explanation**

- **Annotations**:
  - `@Service`: Marks this class as a Spring service component.

- **Configuration Properties**:
  - `jwtExpirationInMs`: JWT token validity duration in milliseconds.
  - `secretKey`: The secret key used for signing JWTs, typically stored securely (e.g., in application properties).

- **Key Methods**:
  - **`generateToken`**:
    - Overloaded methods to generate JWT tokens.
    - Accepts `UserDetails` and optionally additional claims.
    - Constructs the JWT with subject, issued date, expiration, and custom claims (like authorities).
  - **`extractUsername`**:
    - Retrieves the username (subject) from the JWT.
  - **`validateToken`**:
    - Validates the JWT by checking the username and ensuring it's not expired.
  - **`extractClaim`**:
    - Generic method to extract any claim from the JWT.
  - **`extractAllClaims`**:
    - Parses the JWT and retrieves all claims.
  - **`isTokenExpired`**:
    - Checks if the JWT has expired.
  - **`getSignedKey`**:
    - Decodes the Base64-encoded secret key and creates a `SecretKey` for signing/verifying JWTs.

### **Example Usage**

- **Generating a Token**:
  ```java
  UserDetails userDetails = // fetched from UserRepository
  String jwtToken = jwtService.generateToken(userDetails);
  // jwtToken might look like: eyJhbGciOiJIUzI1NiIsInR5cCI6...
  ```

- **Validating a Token**:
  ```java
  boolean isValid = jwtService.validateToken(jwtToken, userDetails);
  ```

- **Extracting Username**:
  ```java
  String username = jwtService.extractUsername(jwtToken);
  ```

---

## 🛡️ 6. **AuthenticationService**

### **AuthenticationService**

```java
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Value("${application.mailing.frontend}")
    private String activationUrl;

    public void register(RegistrationRequest request) throws MessagingException {
        Role userRole = roleRepository.findByName("USER").orElseThrow(
                () -> new IllegalStateException("Role user was not initialized")
        );

        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var token = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                token,
                "Account Activation"
        );
    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);

        Token token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(characters.length());
            char charAt = characters.charAt(index);
            codeBuilder.append(charAt);
        }

        return codeBuilder.toString();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        var claims = new HashMap<String, Object>();

        User user = (User) authenticate.getPrincipal();
        claims.put("fullName", user.getFullName());

        var jwtToken = jwtService.generateToken(claims, user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
```

#### **Explanation**

- **Annotations**:
  - `@Service`: Marks this class as a Spring service component.
  - `@RequiredArgsConstructor`: Generates a constructor with required arguments (i.e., final fields).

- **Dependencies**:
  - **Repositories**:
    - `RoleRepository`: For fetching user roles.
    - `UserRepository`: For managing user data.
    - `TokenRepository`: For managing activation tokens.
  - **Services**:
    - `EmailService`: For sending emails.
    - `AuthenticationManager`: For handling authentication processes.
    - `JwtService`: For generating JWT tokens.
  - **Others**:
    - `PasswordEncoder`: For hashing passwords.
  - **Configuration Properties**:
    - `activationUrl`: The URL for account activation, typically pointing to the frontend application.

- **Methods**:
  - **`register`**:
    - Retrieves the "USER" role from `RoleRepository`.
    - Constructs a new `User` entity with the provided registration details.
    - Encodes the password using `PasswordEncoder`.
    - Saves the user to the database.
    - Sends a validation (activation) email to the user.
  - **`sendValidationEmail`**:
    - Generates an activation token.
    - Sends an email using `EmailService` with the activation link and token.
  - **`generateAndSaveActivationToken`**:
    - Generates a 6-digit activation code.
    - Creates a `Token` entity with the generated code, current time, and expiration time (15 minutes later).
    - Saves the token to the database.
  - **`generateActivationCode`**:
    - Generates a random numeric code of specified length using `SecureRandom`.
  - **`authenticate`**:
    - Creates an `AuthenticationToken` with the user's email and password.
    - Authenticates the user using `AuthenticationManager`.
    - Upon successful authentication, retrieves the `User` entity.
    - Adds additional claims (e.g., `fullName`) to the JWT.
    - Generates a JWT token using `JwtService`.
    - Returns an `AuthenticationResponse` containing the JWT token.

### **Example Workflow**

1. **User Registration**:
   - **Step 1**: User sends a `POST /auth/register` request with registration details.
   - **Step 2**: `AuthenticationService.register(request)` is invoked.
   - **Step 3**: Retrieves the "USER" role.
   - **Step 4**: Creates and saves a new `User` entity with `enabled` set to `false`.
   - **Step 5**: Generates a 6-digit activation code and saves it as a `Token` entity.
   - **Step 6**: Sends an activation email containing the activation link and token to the user's email address.

2. **User Activation**:
   - **Assumption**: An endpoint exists (not shown in the provided code) to handle activation using the token.
   - **Step**: User clicks the activation link, which verifies the token and activates the account by setting `enabled` to `true`.

3. **User Authentication**:
   - **Step 1**: User sends a `POST /auth/authenticate` request with email and password.
   - **Step 2**: `AuthenticationService.authenticate(request)` is invoked.
   - **Step 3**: Creates an `AuthenticationToken` and authenticates the user.
   - **Step 4**: Upon successful authentication, retrieves the `User` entity.
   - **Step 5**: Adds the `fullName` claim to the JWT.
   - **Step 6**: Generates a JWT token with the user's details and claims.
   - **Step 7**: Returns the JWT token in the `AuthenticationResponse`.

---

## 🧩 7. **Supporting Components**

### a. 🫘 **BeansConfig**

Already covered in section ⚙️ 4.

### b. 🗂️ **Entities and Repositories**

While the code for entities (`User`, `Role`, `Token`) and their respective repositories (`UserRepository`, `RoleRepository`, `TokenRepository`) is not provided, their roles can be inferred:

- **`User`**: Represents the user entity with fields like `firstname`, `lastname`, `email`, `password`, `enabled`, `accountLocked`, and `roles`.
- **`Role`**: Represents user roles (e.g., "USER", "ADMIN").
- **`Token`**: Represents activation tokens with fields like `token`, `createdAt`, `expiresAt`, and associated `User`.
- **Repositories**: Extend Spring Data JPA repositories for CRUD operations on these entities.

### c. 📧 **EmailService**

Assumed to handle sending emails based on templates. The method signature suggests parameters like recipient email, recipient name, template name, activation URL, token, and subject.

---

## 🔒 8. **Security Configuration**

### a. 🌱🔐 **Spring Security Integration**

The `BeansConfig` class sets up the essential beans for Spring Security, including the `AuthenticationManager`, `AuthenticationProvider`, and `PasswordEncoder`. Additionally, JWT is used to secure endpoints by issuing tokens upon successful authentication.

### b. 🔄 **JWT-Based Security Flow**

1. **Authentication**:
   - User provides credentials to the `/auth/authenticate` endpoint.
   - Credentials are authenticated, and a JWT is issued if valid.
2. **Authorization**:
   - For secured endpoints, the client includes the JWT in the `Authorization` header as a Bearer token.
   - The server validates the JWT, extracts user details, and grants access accordingly.

### **Example of Secured Endpoint**

Assuming you have a secured endpoint to fetch user details:

```java
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfile> getProfile(Authentication authentication) {
        String email = authentication.getName();
        UserProfile profile = userService.getProfileByEmail(email);
        return ResponseEntity.ok(profile);
    }
}
```

- **Request**:
  ```http
  GET /user/profile
  Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6...
  ```

- **Processing**:
  - Spring Security intercepts the request.
  - Extracts and validates the JWT.
  - Sets the authentication context.
  - The controller method retrieves the authenticated user's profile.

---

## 📚 9. **Best Practices and Considerations**

### a. 🔒 **Password Security**

- **Hashing**: Passwords are hashed using `BCryptPasswordEncoder`, ensuring they are stored securely.
- **Validation**: Passwords are validated for minimum length, preventing weak passwords.

### b. 🔑 **JWT Security**

- **Secret Key Management**: The `secretKey` should be stored securely, such as in environment variables or a secrets manager.
- **Expiration**: JWTs have an expiration time (`jwtExpirationInMs`) to limit their validity period.
- **Claims**: Additional claims (e.g., `fullName`, `authorities`) can be included to provide more context within the token.

### c. 📩 **Account Activation**

- **Token Expiration**: Activation tokens expire after a set duration (15 minutes), reducing the window for misuse.
- **Secure Generation**: Tokens are generated using `SecureRandom`, ensuring unpredictability.

### d. 🛠️ **Error Handling**

- **Exception Management**: The code throws exceptions (e.g., `IllegalStateException`) when critical failures occur, such as missing roles.
- **User Feedback**: Validation annotations provide meaningful error messages to users during registration and authentication.

### e. 📈 **Scalability and Maintenance**

- **Modular Design**: Separation of concerns across controllers, services, and configuration classes promotes maintainability.
- **Extensibility**: Additional features like role-based access control can be easily integrated.

---

## 🚀 10. **Potential Enhancements**

While the current implementation is robust, consider the following enhancements for a production-ready system:

### a. 🔄 **Refresh Tokens**

Implement refresh tokens to allow users to obtain new JWTs without re-authenticating, enhancing user experience and security.

### b. 🔑 **Password Reset Functionality**

Provide endpoints and mechanisms for users to reset their passwords securely.

### c. 🔒 **Account Locking Mechanism**

Implement logic to lock user accounts after multiple failed login attempts, preventing brute-force attacks.

### d. 📝 **Logging and Monitoring**

Incorporate logging for authentication events and monitor for suspicious activities to enhance security.

### e. 🧪 **Testing**

Implement comprehensive unit and integration tests to ensure the reliability and security of the authentication system.

---

## 🎯 11. **Conclusion**

The provided Java code establishes a solid foundation for handling user registration and authentication in a Spring Boot application. By leveraging Spring Security, JWT, and best practices in password handling and user verification, the system ensures secure and efficient authentication processes. Proper understanding and implementation of each component are crucial for building scalable and secure web applications.

---

Feel free to ask for further clarifications or additional details on any part of the implementation!