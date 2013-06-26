<?php

	header('Content-type: text/html; charset=utf-8');

	if (isset($_POST["opcion"]) && $_POST["opcion"] != ""){

		$tag = $_POST["opcion"];

		$con=mysqli_connect("localhost","uees","uees","examen");

		if(mysqli_connect_errno())
		{
			echo 'Could not connect to database: ' . mysqli_connect_error();
			exit();
		}

		if ($tag == "NEWSTUD"){
			$nombre = $_POST["nombre"];
			$matricula_cedula = $_POST["matricula_cedula"];
			$direccion = $_POST["direccion"];
			$telefono = $_POST["telefono"];
			$email = $_POST["email"];
			$carrera = $_POST["carrera"];
			$anio_ingreso = $_POST["anio_ingreso"];
			$curso = $_POST["curso"];

			$resultado = mysqli_query($con,"INSERT INTO estudiante (nombre, matricula_cedula, direccion, telefono, email, carrera, anio_ingreso, curso)
			VALUES ('$nombre', '$matricula_cedula', '$direccion', '$telefono', '$email', '$carrera', '$anio_ingreso', '$curso')");

			mysqli_close($con);

			if ($resultado){
				$salida["exito"]["codigo"] = 1;
				$salida["exito"]["mensaje"] = "Ingreso exitoso.";
			} else {
				$salida["error"]["codigo"] = -1;
				$salida["error"]["mensaje"] = "Ingreso fallido.";
			}
			echo json_encode($salida);

		} else if ($tag == "NEWWSHOP") {
			$nombre = $_POST["nombre"];
			$codigo = $_POST["codigo"];
			$creditos = $_POST["creditos"];
			$profesor = $_POST["profesor"];
			$unidad = $POST["unidad"];
			$fecha_inicio = $POST["fecha_inicio"];
			$horario = $POST["horario"];
			$horas = $POST["horas"];

			$resultado = mysqli_query($con,"INSERT INTO curso (nombre, codigo, creditos, profesor, unidad, fecha_inicio, horario, horas)
			VALUES ('$nombre', '$codigo', '$creditos', '$profesor', '$unidad', '$fecha_inicio', '$horario', '$horas')");

			mysqli_close($con);

			if ($resultado){
				$salida["exito"]["codigo"] = 1;
				$salida["exito"]["mensaje"] = "Ingreso exitoso.";
			} else {
				$salida["error"]["codigo"] = -1;
				$salida["error"]["mensaje"] = "Ingreso fallido.";
			}
			echo json_encode($salida);

		} else if ($tag == "wToSearch") {

			$wToSearch = $_POST["wName"];

			$resultado = mysqli_query($con,"SELECT * FROM curso WHERE nombre LIKE '%$wToSearch%'");

			mysqli_close($con);

			if (!$resultado){
				echo "Query could not be executed. " . mysqli_error($con);
				exit();
			}

			$row =  mysqli_fetch_row($resultado);

			$result_data = array(
				'Nombre' => $row[1],
				'Codigo' => $row[2],
				'Creditos' => $row[3],
				'Profesor' => $row[4],
				'Unidad' => $row[5],
				'FechaInicio' => $row[6],
				'Horario' => $row[7],
				'Horas' => $row[8],
			);
			echo json_encode($result_data);

		}  else if ($tag == "wildSearch") {

			$resultado = mysqli_query($con,"SELECT nombre FROM curso");

			mysqli_close($con);

			if (!$resultado){
				echo "Query could not be executed. " . mysqli_error($con);
				exit();
			}

			while ($row = mysqli_fetch_array($resultado)) {
				$result_data = array(
					'Nombre' => $row['nombre'],
				);
				echo json_encode($result_data);
				echo "<br />";
			}
			//echo json_encode($result_data);

		} else {
			$salida["error"]["codigo"] = -2;
			$salida["error"]["mensaje"] = "INVALID REQUEST";
			echo json_encode($salida);
		}

	} else {
		$salida["error"]["codigo"] = -3;
		$salida["error"]["mensaje"] = "ACCESS DENIED";
		echo json_encode($salida);
	}
?>
