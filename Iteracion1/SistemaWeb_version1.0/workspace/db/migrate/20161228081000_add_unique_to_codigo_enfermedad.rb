class AddUniqueToCodigoEnfermedad < ActiveRecord::Migration
  def change
    add_index_options :enfermedads, :codigo, :unique => true
  end
end
