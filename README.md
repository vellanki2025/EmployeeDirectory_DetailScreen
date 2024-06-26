## Project Tittle 
    Employee Directory

## Project Description
    This project will fetch employee list from the backend.

## Build tools & versions used
    Android Studio: Hedgehog | 2023.1.1 Patch 2
    Gradle: 8.2
    Kotlin: 1.9.0
    SDK Version:
        Compile SDK: 34
        Min SDK: 24
        Target SDK: 34

## Steps to run the app
    Unzip the File:
       Download the provided ZIP file.
       Extract the contents to a desired directory on your local machine.

    Set Up Android Studio:
        Ensure you have Android Studio installed on your machine.
        Open Android Studio and select "Open an existing project."
        Navigate to the directory where the files were extracted and open the project.

    Build and Run the Application:
        Connect your Android device to your machine or set up an Android Virtual Device (AVD) through Android Studio.
        Click on the "Run" button or use the shortcut Shift + F10 to build and run the application.
        The application will be installed and launched on your connected device or emulator.

## What areas of the app did you focus on?
    Architecture:
        Implemented the Model-View-ViewModel (MVVM) pattern to separate concerns and facilitate testing.

    APIs:
        Integrated with the backend by making API calls to fetch the list of employees.

    UI:
        Focused on responsive design.

    Testing:
        Implemented unit tests.

## What was the reason for your focus? What problems were you trying to solve?
    Clean Code:
        Focused on writing clean, readable, and maintainable code to facilitate future development and collaboration.
        Ensured that the codebase adheres to best practices and coding standards to improve readability and reduce technical debt.

    Testable Code:
        Emphasized creating a testable codebase to ensure the reliability and stability of the application.
        Implemented clean architectural patterns and dependency injection to make the code more modular and easier to test.

## How long did you spend on this project?
    I spent a total of 5 hours on this project.

## Did you make any trade-offs for this project?
    Trade-offs Made:
        Local Caching:
            Currently, the app relies solely on real-time data from the backend without local caching, which could affect performance and offline usability.
    
        Use Cases:
            Didn't added UseCase to encapsulate the employee functionality.

        Pagination:
            The initial implementation fetches the entire list of employees at once, which may not be efficient for larger datasets.
    
        Network Connectivity Status:
            Basic handling of network connectivity, relying on the ConnectivityManager for status checks without comprehensive error handling and user notifications.

        Styling:
            Didn't used Material themes for UI.

## What I Would Have Done Differently with More Time:

    Local Caching:
        Introduce local caching to provide a better user experience and support offline access to employee data.

    Adding UseCase:
        Add a UseCase to the domain layer to encapsulate the employee functionality.

    Add Pagination:
        Implement pagination to handle large datasets efficiently and improve performance.

    Improving Network Connectivity Status:
        Enhance the network connectivity handling to provide better user feedback and resilience to network issues.

    Styling:
        Use Material themes to improve the user experience using primary and secondary colors.

    Compatibility with Various Devices and Screen Sizes:
        Ensure the application is fully compatible with a wide range of Android devices and screen sizes.

    UI Testing:
        Add support for UI testing.

    Access Management
        Each employee's access rights are defined and enforced to ensure that they can only access 
        resources and functionalities they are authorized to use. This ensures security and proper access 
        control within the organization's systems.

## What do you think is the weakest part of your project?
    The absence of local caching means the app does not offer offline access, 
    which can be inconvenient for users with intermittent internet connections.

## Did you copy any code or dependencies? Please make sure to attribute them here!
    I used third-party libraries to enhance the functionality and development process of the application. The following library was used:
    Retrofit: HTTP client for Android, used for making API calls. 
    Glide: An image loading and caching library for Android, used for efficiently loading and displaying images.

## Is there any other information you’d like us to know?
    I am committed to continuously improving this application and welcome any feedback or suggestions for future enhancements. 
    Additionally, I am open to collaborating with others to expand the functionality and features of the app. 
    Please feel free to reach out for any inquiries or potential collaborations.
