# Login Example with MVVM, DataBinding With LiveData

This is a very simple **Login Example** using **MVVM pattern and DataBinding and LiveData** in Android. It takes input from the UI using DataBinding **"@="**, stores it in LiveData and displays back on the UI.

This example is for those who want to learn the easiest way to get data from UI. This is useful in many ways such as Saving Development Time, Code Reusability, Validations etc. No wonder it is being used all over Android Community.

So let's get started on using these technologies together in a single application:

1. What is MVVM?
2. What is DataBinding?
3. What is LiveData?
4. Conclusion

# What is MVVM

**Answer:** MVVM is a design pattern for organizing GUI applications that has become popular on Android.

This concept will introduce you to the main 3 components of MVVM, the View, Model, and ViewModel.

The Model View ViewModel (MVVM) is an architectural pattern used in software engineering that originated from Microsoft which is specialized in the Presentation Model design pattern. It is based on the Model-view-controller pattern (MVC), and is targeted at modern UI development platforms (WPF and Silverlight) in which there is a UX developer who has different requirements than a more "traditional" developer. MVVM is a way of creating client applications that leverages core features of the WPF platform, allows for simple unit testing of application functionality, and helps developers and designers work together with less technical difficulties.

**View:** A View is defined in XAML and should not have any logic in the code-behind. It binds to the view-model by only using data binding.

**Model:** A Model is responsible for exposing data in a way that is easily consumable by WPF. It must implement INotifyPropertyChanged and/or INotifyCollectionChanged as appropriate.

**ViewModel:** A ViewModel is a model for a view in the application or we can say as abstraction of the view. It exposes data relevant to the view and exposes the behaviors for the views, usually with Commands.

**Model:**

*   Definition, roles and responsibilities.
*   What should go in your model layer and what shouldn't.
*   Benefits of model isolation and how it affects testing.

**View:**

*   Definition, roles and responsibilities.
*   How it interacts with the ViewModel.

**ViewModel:**

*   Definition, roles and responsibilities.
*   How it supports the View, by providing actions and observable state.
*   Interactions with the Model.
*   Isolation from the View.

[![2](https://camo.githubusercontent.com/81cc5c093bdd9590ee9b72fde98819e5da3e8f06f782973c2c37643f8f17b78d/68747470733a2f2f696d6167652e6962622e636f2f6e6d68784e4b2f322e706e67)](https://camo.githubusercontent.com/81cc5c093bdd9590ee9b72fde98819e5da3e8f06f782973c2c37643f8f17b78d/68747470733a2f2f696d6167652e6962622e636f2f6e6d68784e4b2f322e706e67)

# What is DataBinding
------------------------------------------------

**Answer:** The Data Binding Library is a support library that allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically and many more.

[![3](https://camo.githubusercontent.com/48e678bac063a060202b79169f65a1ca91b8701e2ad63434e222833602dc5531/68747470733a2f2f707265766965772e6962622e636f2f6964444b6d652f332e706e67)](https://camo.githubusercontent.com/48e678bac063a060202b79169f65a1ca91b8701e2ad63434e222833602dc5531/68747470733a2f2f707265766965772e6962622e636f2f6964444b6d652f332e706e67)

# What is LiveData

**Answer:** LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.

Using LiveData provides the following advantages:

**Ensures your UI matches your data state:** LiveData follows the observer pattern. LiveData notifies Observer objects when the lifecycle state changes. You can consolidate your code to update the UI in these Observer objects. Instead of updating the UI every time the app data changes, your observer can update the UI every time there's a change.

**No memory leaks:** Observers are bound to Lifecycle objects and clean up after themselves when their associated lifecycle is destroyed.

**No crashes due to stopped activities:** If the observer's lifecycle is inactive, such as in the case of an activity in the back stack, then it doesn’t receive any LiveData events.

**No more manual lifecycle handling:** UI components just observe relevant data and don’t stop or resume observation. LiveData automatically manages all of this since it’s aware of the relevant lifecycle status changes while observing.

**Always up to date data:** If a lifecycle becomes inactive, it receives the latest data upon becoming active again. For example, an activity that was in the background receives the latest data right after it returns to the foreground.

**Proper configuration changes:** If an activity or fragment is recreated due to a configuration change, like device rotation, it immediately receives the latest available data.

**Sharing resources:** You can extend a LiveData object using the singleton pattern to wrap system services so that they can be shared in your app. The LiveData object connects to the system service once, and then any observer that needs the resource can just watch the LiveData object. For more information, see Extend LiveData.

# Conclusion

Hopefully this guide should have helped you in making your tasks really easier in terms of many things, such as: eliminating **findViewById(...)** and many more.
