class AddUniqueToCedulaPacientes < ActiveRecord::Migration
  def change
    add_index_options :pacientes, :cedula, :unique => true
  end
end
