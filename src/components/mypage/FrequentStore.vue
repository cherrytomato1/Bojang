<template>
  <v-app>
    <v-main>
      <v-container>
        <!-- grid로 조절했는데 아직 한줄로 안됨 조절해보기-->
        <v-row>
          <v-col
            cols="8"
          >
            <h2>
              단골 가게 관리
            </h2>
          </v-col>
          <v-col
            cols="4"
          >
            <v-text-field
              flat
              hide-details
              label="Search"
              prepend-inner-icon="mdi-magnify"
              solo-inverted
            />
          </v-col>
        </v-row>
      </v-container>
      <v-container>
        <v-row>
          <v-col
            cols="2"
          >
            <!-- 체크가 왜 안되지? -->

            <v-checkbox
              label="전체선택"
              value
            />
          </v-col>
          <v-col
            cols="2"
          >
            <v-btn>
              선택삭제
            </v-btn>
          </v-col>
        </v-row>
      </v-container>
      <v-form>
        <v-container>
          <v-row>
            <v-col
              cols="2"
            >
              <!-- 체크가 왜 안되지? -->
              <v-checkbox value />
            </v-col>
            <v-col
              cols="2"
            >
              <!-- size mx-auto가 안되네 추후 설정 -->
              <img
                src="@/assets/logo.png"
                alt="가게 사진"
                style="width:60px"
              >
            </v-col>

            <v-col
              cols="6"
            >
              <!-- 입력한 필드가 아닌 데이터를 가져와야됨 변경필요 -->
              <v-text-field
                v-model="message"
                :prepend-icon="icon"
                clearable
                label="Message"
                type="text"
                @click:append="toggleMarker"
                @click:append-outer="sendMessage"
                @click:prepend="changeIcon"
                @click:clear="clearMessage"
              />
            </v-col>
            <v-col
              cols="2"
            >
              <!-- x 표시 아이콘 찾으면 적용해주세요 -->
              <v-icon>x</v-icon>
            </v-col>
          </v-row>
        </v-container>
        <v-container>
          <v-row>
            <v-col
              cols="2"
            >
              <!-- 체크가 왜 안되지? -->
              <v-checkbox value />
            </v-col>
            <v-col
              cols="2"
            >
              <!-- size mx-auto가 안되네 추후 설정 -->
              <img
                src="@/assets/logo.png"
                alt="가게 사진"
                style="width:60px"
              >
            </v-col>

            <v-col
              cols="6"
            >
              <!-- 입력한 필드가 아닌 데이터를 가져와야됨 변경필요 -->
              <v-text-field
                v-model="message"
                :prepend-icon="icon"
                clearable
                label="Message"
                type="text"
                @click:append="toggleMarker"
                @click:append-outer="sendMessage"
                @click:prepend="changeIcon"
                @click:clear="clearMessage"
              />
            </v-col>
            <v-col
              cols="2"
            >
              <!-- x 표시 아이콘 찾으면 적용해주세요 -->
              <v-icon>x</v-icon>
            </v-col>
          </v-row>
        </v-container>
      </v-form>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: 'FrequentStore',
}
</script>

<style>

</style>
