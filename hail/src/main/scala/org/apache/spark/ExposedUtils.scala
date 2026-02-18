package org.apache.spark

import scala.reflect._

import org.apache.spark.serializer._
import org.apache.spark.util._

object ExposedUtils {
  def clean[F <: AnyRef](f: F, checkSerializable: Boolean = true): F = {
    ClosureCleaner.clean(f, checkSerializable, scala.collection.mutable.Map.empty)
    f
  }

  def clone[T: ClassTag](value: T, sc: SparkContext): T =
    clone(value, sc.env.serializer.newInstance())

  def clone[T: ClassTag](value: T, serializer: SerializerInstance): T =
    Utils.clone(value, serializer)
}
