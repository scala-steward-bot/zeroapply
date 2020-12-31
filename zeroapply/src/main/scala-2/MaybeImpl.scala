package zeroapply

import scala.reflect.macros.blackbox.Context

final class MaybeImpl(override val c: Context) extends OptionBase {
  import c.universe._

  override protected def getValDef(name: TermName, tpe: Type, body: Tree): Tree =
    q"val $name: _root_.scalaz.Maybe[$tpe] = $body"

  override protected def getValue(value: Tree, tpe: Type): Tree =
    q"$value.asInstanceOf[_root_.scalaz.Maybe.Just[$tpe]].get"

  override protected def none(value: Tree): Tree =
    q"_root_.scalaz.Maybe.Empty()"

  override protected def wrapSome(value: Tree): Tree =
    q"_root_.scalaz.Maybe.Just($value)"

  override protected def isEmpty(value: Tree): Tree =
    q"$value.isInstanceOf[_root_.scalaz.Maybe.Empty[_]]"
}
