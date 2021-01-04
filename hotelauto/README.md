* `hotel` package contains all Hotel domain-related POJOs (plain old Java objects)
* There's a `SensorService` which receives signals from its sensors regarding which corridor is seeing some movement.
  * It receives the signal and then forwards it to `HotelAutomation` object
  * It provides what type of signal (movement or not) and from which corridor it received that signal
* `HotelAutomation` when receives movement signal tells the target corridor to immediately turn on the light
* `HotelAutomation` maintains a stack 