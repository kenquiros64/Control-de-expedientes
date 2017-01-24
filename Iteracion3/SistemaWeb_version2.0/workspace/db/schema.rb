# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20170107181041) do

  create_table "cita", force: :cascade do |t|
    t.integer  "medico_id",   limit: 4
    t.datetime "created_at",            null: false
    t.datetime "updated_at",            null: false
    t.integer  "paciente_id", limit: 4
    t.date     "fecha"
    t.time     "hora"
  end

  add_index "cita", ["medico_id"], name: "index_cita_on_medico_id", using: :btree
  add_index "cita", ["paciente_id"], name: "index_cita_on_pacientes_id", using: :btree

  create_table "emails", force: :cascade do |t|
    t.string   "email",       limit: 255
    t.integer  "paciente_id", limit: 4
    t.datetime "created_at",              null: false
    t.datetime "updated_at",              null: false
  end

  add_index "emails", ["paciente_id"], name: "index_emails_on_paciente_id", using: :btree

  create_table "enfermedads", force: :cascade do |t|
    t.string   "codigo",      limit: 255
    t.string   "descripcion", limit: 255
    t.datetime "created_at",              null: false
    t.datetime "updated_at",              null: false
  end

  create_table "enfermedads_pacientes", id: false, force: :cascade do |t|
    t.integer "enfermedad_id", limit: 4
    t.integer "paciente_id",   limit: 4
  end

  add_index "enfermedads_pacientes", ["enfermedad_id"], name: "index_enfermedads_pacientes_on_enfermedad_id", using: :btree
  add_index "enfermedads_pacientes", ["paciente_id"], name: "index_enfermedads_pacientes_on_paciente_id", using: :btree

  create_table "medicos", force: :cascade do |t|
    t.string   "email",                  limit: 255, default: "", null: false
    t.string   "encrypted_password",     limit: 255, default: "", null: false
    t.string   "reset_password_token",   limit: 255
    t.datetime "reset_password_sent_at"
    t.datetime "remember_created_at"
    t.integer  "sign_in_count",          limit: 4,   default: 0,  null: false
    t.datetime "current_sign_in_at"
    t.datetime "last_sign_in_at"
    t.string   "current_sign_in_ip",     limit: 255
    t.string   "last_sign_in_ip",        limit: 255
    t.datetime "created_at",                                      null: false
    t.datetime "updated_at",                                      null: false
    t.string   "codigo",                 limit: 255
    t.string   "nombre",                 limit: 255
    t.string   "apellido1",              limit: 255
    t.string   "apellido2",              limit: 255
    t.string   "cedula",                 limit: 255
    t.string   "nacionalidad",           limit: 255
    t.string   "passcode",               limit: 255
    t.boolean  "activo"
  end

  add_index "medicos", ["email"], name: "index_medicos_on_email", unique: true, using: :btree
  add_index "medicos", ["reset_password_token"], name: "index_medicos_on_reset_password_token", unique: true, using: :btree

  create_table "medicos_pacientes", id: false, force: :cascade do |t|
    t.integer "medico_id",   limit: 4
    t.integer "paciente_id", limit: 4
  end

  add_index "medicos_pacientes", ["medico_id"], name: "index_medicos_pacientes_on_medico_id", using: :btree
  add_index "medicos_pacientes", ["paciente_id"], name: "index_medicos_pacientes_on_paciente_id", using: :btree

  create_table "medicos_roles", id: false, force: :cascade do |t|
    t.integer "medico_id", limit: 4
    t.integer "role_id",   limit: 4
  end

  add_index "medicos_roles", ["medico_id", "role_id"], name: "index_medicos_roles_on_medico_id_and_role_id", using: :btree

  create_table "observacions", force: :cascade do |t|
    t.text     "descripcion", limit: 65535, null: false
    t.datetime "created_at",                null: false
    t.datetime "updated_at",                null: false
    t.integer  "medico_id",   limit: 4
  end

  add_index "observacions", ["medico_id"], name: "index_observacions_on_medico_id", using: :btree

  create_table "observacions_citas", id: false, force: :cascade do |t|
    t.integer "observacion_id", limit: 4
    t.integer "citum_id",       limit: 4
  end

  add_index "observacions_citas", ["citum_id"], name: "index_observacions_citas_on_cita_id", using: :btree
  add_index "observacions_citas", ["observacion_id"], name: "index_observacions_citas_on_observacion_id", using: :btree

  create_table "observacions_pacientes", id: false, force: :cascade do |t|
    t.integer "observacion_id", limit: 4
    t.integer "paciente_id",    limit: 4
  end

  add_index "observacions_pacientes", ["observacion_id"], name: "index_observacions_pacientes_on_observacion_id", using: :btree
  add_index "observacions_pacientes", ["paciente_id"], name: "index_observacions_pacientes_on_paciente_id", using: :btree

  create_table "pacientes", force: :cascade do |t|
    t.string   "cedula",             limit: 255
    t.string   "nombre",             limit: 255
    t.string   "apellido1",          limit: 255
    t.string   "apellido2",          limit: 255
    t.date     "fechaNacimiento"
    t.string   "nacionalidad",       limit: 255
    t.string   "sexo",               limit: 255
    t.date     "fechaFallecimiento"
    t.datetime "created_at",                     null: false
    t.datetime "updated_at",                     null: false
    t.string   "image_file_name",    limit: 255
    t.string   "image_content_type", limit: 255
    t.integer  "image_file_size",    limit: 4
    t.datetime "image_updated_at"
  end

  create_table "roles", force: :cascade do |t|
    t.string   "name",          limit: 255
    t.integer  "resource_id",   limit: 4
    t.string   "resource_type", limit: 255
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  add_index "roles", ["name", "resource_type", "resource_id"], name: "index_roles_on_name_and_resource_type_and_resource_id", using: :btree
  add_index "roles", ["name"], name: "index_roles_on_name", using: :btree

  create_table "telefonos", force: :cascade do |t|
    t.string   "telefono",    limit: 255
    t.integer  "paciente_id", limit: 4
    t.datetime "created_at",              null: false
    t.datetime "updated_at",              null: false
  end

  add_index "telefonos", ["paciente_id"], name: "index_telefonos_on_paciente_id", using: :btree

  add_foreign_key "cita", "medicos"
  add_foreign_key "emails", "pacientes"
  add_foreign_key "observacions", "medicos"
  add_foreign_key "telefonos", "pacientes"
end
