package ce.injection.samples
package model

case class Conf(ce: CE)
case class CE(injection: Injection)
case class Injection(samples: InjectionSamples)
case class InjectionSamples(project: InjectionProject)
case class InjectionProject(name: String)
