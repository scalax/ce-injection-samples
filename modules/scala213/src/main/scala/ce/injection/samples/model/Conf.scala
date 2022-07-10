package ce.injection.samples
package model

case class Conf(ce: CE)
case class CE(injection: Injection)
case class Injection(samples: InjectionSamples)
case class InjectionSamples(project: InjectionProject)
case class InjectionProject(name: String, init: ProjectInit)
case class ProjectInit(h2: ProjectH2)
case class ProjectH2(cats: List[ProjectCats])
case class ProjectCats(name: String, age: Int)
